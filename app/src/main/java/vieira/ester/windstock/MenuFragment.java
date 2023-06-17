package vieira.ester.windstock;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URI;

import com.bumptech.glide.Glide;

import vieira.ester.windstock.databinding.FragmentLoginBinding;
import vieira.ester.windstock.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    FragmentMenuBinding menuBinding;
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
        menuBinding.textNomeUsuario.setText(nomeUsuario);

        if (fotoUsuario != null) {

            Glide.with(requireContext())
                    .load(fotoUsuario)
                    .placeholder(R.drawable.ic_foto)
                    .error(R.drawable.ic_foto)
                    .into(menuBinding.imageFotoUsuario);
        }

        return menuBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuBinding.btnTransacao.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_transacaoFragment, getArguments()));
    }
}