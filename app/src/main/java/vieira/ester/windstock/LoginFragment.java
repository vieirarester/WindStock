package vieira.ester.windstock;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import vieira.ester.windstock.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    FragmentLoginBinding loginBinding;

    private static final String TAG = "LoginFragment";
    private static final int RC_SIGN_IN = 9001;

    GoogleSignInClient googleSignInClient;


    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        loginBinding.btnLoginGoogle.setOnClickListener(this);
        return loginBinding.getRoot();

    }

    @Override
    public void onClick(View v) {
        Intent loginIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(loginIntent, RC_SIGN_IN);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount conta = completedTask.getResult(ApiException.class);

            if (conta != null) {
                Bundle usuario = new Bundle();
                usuario.putString("nome", conta.getDisplayName());
                usuario.putParcelable("foto", conta.getPhotoUrl());

                Navigation.findNavController(requireView()).navigate(R.id.menuFragment, usuario);
            }
        } catch (ApiException e) {
            Log.e(TAG, "handleSignInResult: failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}