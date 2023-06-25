package vieira.ester.windstock;

import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import vieira.ester.windstock.databinding.FragmentBuscarBinding;

public class BuscarFragment extends Fragment {

    private FragmentBuscarBinding buscarBinding;
    private String codigoQrCode;

    private boolean requisitadoInstalacaoAr = true;
    private Session sessao;

    ArFragment arFragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.arFragment);

    public BuscarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        buscarBinding = FragmentBuscarBinding.inflate(inflater, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return buscarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**buscarBinding.btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanner = new IntentIntegrator(requireActivity());
                scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                scanner.setBeepEnabled(false);
                scanner.initiateScan();
            }
        });

        buscarBinding.textQrCode.setText(codigoQrCode);**/

        carregarCard();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && requestCode == IntentIntegrator.REQUEST_CODE) {
            if (result.getContents() == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                codigoQrCode = result.getContents();
                //buscarBinding.textQrCode.setText(codigoQrCode);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            if (sessao == null && ehVersaoAtualizada()) {

                switch (ArCoreApk.getInstance().requestInstall(requireActivity(), requisitadoInstalacaoAr)) {
                    case INSTALLED:
                        // Success: Safe to create the AR session.
                        sessao = new Session(requireContext());
                        break;
                    case INSTALL_REQUESTED:
                        // O ARCore solicitou a instalação ou atualização do Google Play Services for AR

                        // 1. Pausar a atividade
                        onPause();
                        // 2. Solicitar a instalação ou atualização do Google Play Services for AR
                        ArCoreApk.InstallStatus installStatusDeferred = ArCoreApk.getInstance().requestInstall(requireActivity(), true);
                        if (installStatusDeferred != ArCoreApk.InstallStatus.INSTALLED) {
                            // A instalação ou atualização não foi concluída com sucesso
                            Toast.makeText(requireContext(), "A instalação ou atualização do ARCore falhou.", Toast.LENGTH_LONG).show();
                        }
                        // 3. Retomar a atividade após a conclusão
                        onResume();
                        break;
                }
            }
        } catch (UnavailableUserDeclinedInstallationException e) {
            Toast.makeText(requireContext(), "O usuário recusou a instalação do ARCore.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Ocorreu um erro durante a inicialização do AR.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (sessao == null){
            arFragment.onPause();
            sessao.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(sessao != null) {
            sessao.close();
            sessao = null;
        }
    }

    private boolean ehVersaoAtualizada() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(requireContext());
        switch (availability) {
            case SUPPORTED_INSTALLED:
                return true;

            case SUPPORTED_APK_TOO_OLD:
            case SUPPORTED_NOT_INSTALLED:
                try {
                    ArCoreApk.InstallStatus installStatus = ArCoreApk.getInstance().requestInstall(requireActivity(), true);
                    switch (installStatus) {
                        case INSTALL_REQUESTED:
                            return false;
                        case INSTALLED:
                            return true;
                    }
                } catch (UnavailableException e) {
                    e.printStackTrace();
                    return false;
                }
                return false;

            case UNSUPPORTED_DEVICE_NOT_CAPABLE:
                // Este dispositivo não é compatível com AR.
                return false;

            case UNKNOWN_CHECKING:
                // O ARCore está verificando a disponibilidade com uma consulta remota.
                // Esta função deve ser chamada novamente após esperar 200 ms para determinar o resultado da consulta.
                return false;

            case UNKNOWN_ERROR:
            case UNKNOWN_TIMED_OUT:
                // Ocorreu um erro ao verificar a disponibilidade do AR. Isso pode ocorrer porque o dispositivo está off-line.
                return false;
        }

        return false;
    }

    public void criarSessao() throws UnavailableDeviceNotCompatibleException, UnavailableSdkTooOldException, UnavailableArcoreNotInstalledException, UnavailableApkTooOldException {
        sessao = new Session(requireContext());

        Config configuracao = new Config(sessao);

        sessao.configure(configuracao);
    }

    private void carregarCard() {


    }

}