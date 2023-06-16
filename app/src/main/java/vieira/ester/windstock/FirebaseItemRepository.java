package vieira.ester.windstock;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseItemRepository implements ItemRepository{

    private FirebaseFirestore bancoDados;

    public FirebaseItemRepository() {
        bancoDados = FirebaseFirestore.getInstance();
    }

    @Override
    public void adicionarItem(Item item, OnCompleteListener<DocumentReference> onCompleteListener) {
        CollectionReference itensRef = bancoDados.collection("itens");
        itensRef.add(item).addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void listarItens(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        CollectionReference itensRef = bancoDados.collection("itens");
        itensRef.get().addOnCompleteListener(onCompleteListener);
    }
}
