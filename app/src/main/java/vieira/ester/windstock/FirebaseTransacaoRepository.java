package vieira.ester.windstock;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

public class FirebaseTransacaoRepository implements TransacaoRepository {
    private FirebaseFirestore bancoDados;
    private CollectionReference transacoesRef;
    private Context contexto;

    public FirebaseTransacaoRepository(Context contexto) {
        this.bancoDados = FirebaseFirestore.getInstance();
        this.transacoesRef = bancoDados.collection("transacoes");
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

                    }
                });
    }

    @Override
    public void listarTodas(final OnDataListener<List<Transacao>> listener) {
        transacoesRef.orderBy("data", Query.Direction.DESCENDING).get()
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

    @Override
    public void calcularTotalEntradas(OnDataListener<Integer> listener) {

        Query consulta = transacoesRef.whereEqualTo("tipo", "Entrada");
        consulta.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int totalEntradas = 0;
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Transacao transacao = document.toObject(Transacao.class);
                    totalEntradas += 1;
                }
                listener.onSuccess(totalEntradas);
            } else {
                listener.onFailure(new Exception());
            }
        });
    }

    @Override
    public void calcularTotalSaidas(OnDataListener<Integer> listener) {

        Query consulta = transacoesRef.whereEqualTo("tipo", "Saída");
        consulta.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int totalSaidas = 0;
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Transacao transacao = document.toObject(Transacao.class);
                    totalSaidas += 1;
                }
                listener.onSuccess(totalSaidas);
            } else {
                listener.onFailure(new Exception());
            }
        });
    }
}


