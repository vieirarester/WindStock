package vieira.ester.windstock;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

public interface TransacaoRepository {

    void adicionarItem(Transacao transacao, OnCompleteListener<DocumentReference> onCompleteListener);
    void listarItens(OnCompleteListener<QuerySnapshot> onCompleteListener);
}
