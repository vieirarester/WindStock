package vieira.ester.windstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vieira.ester.windstock.databinding.FragmentRelatorioBinding;

public class RelatorioFragment extends Fragment {

    private FragmentRelatorioBinding relatorioBinding;
    FirebaseTransacaoRepository transacaoRepository;
    private ViewModelRelatorio viewModelRelatorio;

    public RelatorioFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        relatorioBinding = FragmentRelatorioBinding.inflate(inflater, container, false);
        transacaoRepository = new FirebaseTransacaoRepository(requireContext());
        viewModelRelatorio = new ViewModelProvider(this).get(ViewModelRelatorio.class);

        atualizarEstoque();

        return relatorioBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void atualizarTextViews(){
        viewModelRelatorio.getEntradasEscovaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtEntEscova.setText(integer.toString());
            }
        });

        viewModelRelatorio.getSaidasEscovaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtSaiEscova.setText(integer.toString());
            }
        });

        viewModelRelatorio.getEntradasBateriaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtEntBateria.setText(integer.toString());
            }
        });

        viewModelRelatorio.getSaidasBateriaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtSaiBateria.setText(integer.toString());
            }
        });

        viewModelRelatorio.getEntradasRespiradorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtEntRespirador.setText(integer.toString());
            }
        });

        viewModelRelatorio.getSaidasRespiradorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtSaiRespirador.setText(integer.toString());
            }
        });

        viewModelRelatorio.getEntradasFusivelLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtEntFusivel.setText(integer.toString());
            }
        });

        viewModelRelatorio.getSaidasFusivelLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtSaiFusivel.setText(integer.toString());
            }
        });

        viewModelRelatorio.getEntradasLampadaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtEntLampada.setText(integer.toString());
            }
        });

        viewModelRelatorio.getSaidasLampadaLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                relatorioBinding.txtSaiLampada.setText(integer.toString());
            }
        });
    }

    public void atualizarEstoque(){
        transacaoRepository.calcularEntradas(entradasEscova, getString(R.string.escova));
        transacaoRepository.calcularSaidas(saidasEscova, getString(R.string.escova));

        transacaoRepository.calcularEntradas(entradasBateria, getString(R.string.bateria));
        transacaoRepository.calcularSaidas(saidasBateria, getString(R.string.bateria));

        transacaoRepository.calcularEntradas(entradasRespirador, getString(R.string.respirador));
        transacaoRepository.calcularSaidas(saidasRespirador, getString(R.string.respirador));

        transacaoRepository.calcularEntradas(entradasFusivel, getString(R.string.fusivel));
        transacaoRepository.calcularSaidas(saidasFusivel, getString(R.string.fusivel));

        transacaoRepository.calcularEntradas(entradasLampada, getString(R.string.lampada));
        transacaoRepository.calcularSaidas(saidasLampada, getString(R.string.lampada));
    }

    public OnDataListener<Integer> entradasEscova = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            relatorioBinding.txtEntEscova.setText(String.valueOf(totalEntradas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidasEscova = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            relatorioBinding.txtSaiEscova.setText(String.valueOf(totalSaidas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> entradasBateria = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            relatorioBinding.txtEntBateria.setText(String.valueOf(totalEntradas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidasBateria = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            relatorioBinding.txtSaiBateria.setText(String.valueOf(totalSaidas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> entradasRespirador = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            relatorioBinding.txtEntRespirador.setText(String.valueOf(totalEntradas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidasRespirador = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            relatorioBinding.txtSaiRespirador.setText(String.valueOf(totalSaidas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> entradasFusivel = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            relatorioBinding.txtEntFusivel.setText(String.valueOf(totalEntradas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidasFusivel = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            relatorioBinding.txtSaiFusivel.setText(String.valueOf(totalSaidas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> entradasLampada = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalEntradas) {
            relatorioBinding.txtEntLampada.setText(String.valueOf(totalEntradas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    public OnDataListener<Integer> saidasLampada = new OnDataListener<Integer>() {
        @Override
        public void onSuccess(Integer totalSaidas) {
            relatorioBinding.txtSaiLampada.setText(String.valueOf(totalSaidas));
        }

        @Override
        public void onFailure(Exception e) {
        }
    };
}