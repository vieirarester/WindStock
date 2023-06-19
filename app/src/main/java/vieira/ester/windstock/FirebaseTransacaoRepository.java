package vieira.ester.windstock;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

public class FirebaseTransacaoRepository implements TransacaoRepository{
    private FirebaseFirestore bancoDados;
    private CollectionReference transacoesRef;
    private Context contexto;

    public FirebaseTransacaoRepository(Context contexto) {
        this.bancoDados = FirebaseFirestore.getInstance();
        this.transacoesRef = bancoDados.collection("transacoes"); // Nome da coleção no Firestore
        this.contexto = contexto;
    }

    @Override
    public void adicionar(Transacao transacao, View view) {
        transacoesRef.add(transacao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(contexto, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.historicoFragment);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Erro ao adicionar a transação
                    }
                });
    }
    @Override
    public void listarTodas(final OnDataListener<List<Transacao>> listener) {
        transacoesRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Transacao> transacoes = new ArrayList<>();
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Transacao transacao = document.toObject(Transacao.class);
                            transacoes.add(transacao);
                        }
                        listener.onSuccess(transacoes);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e);
                    }
                });
    }

    public interface OnDataListener<T> {
        void onSuccess(T data);
        void onFailure(Exception e);
    }

}
