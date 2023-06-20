package vieira.ester.windstock;

import static org.mockito.Mockito.*;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.DocumentReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import vieira.ester.windstock.FirebaseTransacaoRepository;
import vieira.ester.windstock.R;
import vieira.ester.windstock.Transacao;

@RunWith(RobolectricTestRunner.class)
public class FirebaseTransacaoRepositoryTest {
    FirebaseTransacaoRepository transacoes;

    @Mock
    Toast toast;

    @Mock
    NavController navController;

    @Mock
    View view;

    @Mock
    Context context;

    @Before
    public void inicializar() {
        transacoes = new FirebaseTransacaoRepository(context);

        MockitoAnnotations.initMocks(this);

        when(view.getContext()).thenReturn(context);
        when(context.getApplicationContext()).thenReturn(context);
        when(context.getString(anyInt())).thenReturn("Transação salva com sucesso!");
        when(Toast.makeText(any(Context.class), anyString(), anyInt())).thenReturn(toast);
        when(Navigation.findNavController(view)).thenReturn(navController);
    }

    @Test
    public void adicionarTest() {
        Transacao transacao = new Transacao();

        // Executar o método que será testado
        transacoes.adicionar(transacao, view);

        // Verificar se os métodos corretos foram chamados
        verify(toast).show();
        verify(navController).navigate(R.id.historicoFragment);
    }
}
