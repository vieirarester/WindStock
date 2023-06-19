package vieira.ester.windstock;

import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface TransacaoRepository {

    void adicionar(Transacao transacao, View view);
    void listarTodas(final FirebaseTransacaoRepository.OnDataListener<List<Transacao>> listener);

}
