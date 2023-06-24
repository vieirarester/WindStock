package vieira.ester.windstock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.URI;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import vieira.ester.windstock.databinding.FragmentLoginBinding;
import vieira.ester.windstock.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    FragmentMenuBinding menuBinding;
    FirebaseTransacaoRepository transacaoRepository;
    Context contexto;
    String nomeUsuario;
    Uri fotoUsuario;

    public MenuFragment() {
        super(R.layout.fragment_menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            nomeUsuario = getArguments().getString("nome");
            fotoUsuario = getArguments().getParcelable("foto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        menuBinding = FragmentMenuBinding.inflate(inflater, container, false);
        transacaoRepository = new FirebaseTransacaoRepository(requireContext());
        menuBinding.textNomeUsuario.setText(nomeUsuario);
        contexto = requireContext();

        if (fotoUsuario != null) {

            Glide.with(requireContext())
                    .load(fotoUsuario)
                    .placeholder(R.drawable.ic_foto)
                    .error(R.drawable.ic_foto)
                    .into(menuBinding.imageFotoUsuario);
        }

        transacaoRepository.calcularTotalEntradas(entradas);
        transacaoRepository.calcularTotalSaidas(saidas);
        menuBinding.txtValorEstoque.setText(String.valueOf(estoqueAtual));

        return menuBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuBinding.btnTransacao.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_transacaoFragment, getArguments()));
        menuBinding.btnHistorico.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_historicoFragment, null));
        menuBinding.btnRelatorio.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_relatorioFragment, null));
        menuBinding.btnBuscar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_buscarFragment, null));

        menuBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(requireContext());
            }
        });
    }

    public void logout(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);
        googleSignInClient.signOut()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Usuário desconectado!", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(requireView()).navigate(R.id.action_menuFragment_to_loginFragment);
                    }
                });

    }
    int estoqueAtual = 0;
    public OnDataListener<Integer> entradas = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            menuBinding.txtValorEntrada.setText(String.valueOf(totalEntradas));
            estoqueAtual = totalEntradas;
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidas = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            menuBinding.txtValorSaida.setText(String.valueOf(totalSaidas));
            estoqueAtual -= totalSaidas;
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

}