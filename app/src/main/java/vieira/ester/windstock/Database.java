package vieira.ester.windstock;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import vieira.ester.windstock.Transacao;

public class Database {
    private FirebaseFirestore db;
    private CollectionReference transacoesRef;

    public Database() {
        db = FirebaseFirestore.getInstance();
        transacoesRef = db.collection("transacoes"); // Nome da coleção no Firestore
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoesRef.add(transacao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Transação adicionada com sucesso
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Erro ao adicionar a transação
                    }
                });
    }

    public void obterTransacoes(final OnDataListener<List<Transacao>> listener) {
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
