package vieira.ester.windstock;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseTransacaoRepository implements TransacaoRepository {

    private FirebaseFirestore bancoDados;

    public FirebaseTransacaoRepository() {
        bancoDados = FirebaseFirestore.getInstance();
    }

    @Override
    public void adicionarTransacao(Transacao transacao, OnCompleteListener<DocumentReference> onCompleteListener) {
        CollectionReference transacoesRef = bancoDados.collection("transacoes");
        transacoesRef.add(transacao).addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void listarTransacoes(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        CollectionReference itensRef = bancoDados.collection("itens");
        itensRef.get().addOnCompleteListener(onCompleteListener);
    }
}
