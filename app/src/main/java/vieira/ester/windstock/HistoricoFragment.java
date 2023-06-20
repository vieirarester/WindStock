package vieira.ester.windstock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vieira.ester.windstock.databinding.FragmentHistoricoBinding;

public class HistoricoFragment extends Fragment {

    FragmentHistoricoBinding historicoBinding;
    RecyclerView recyclerView;
    TransacaoAdapter adapter;

    FirebaseTransacaoRepository transacaoRepository;
    private List<Transacao> transacoes;

    public HistoricoFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        transacaoRepository = new FirebaseTransacaoRepository(requireContext());

        historicoBinding = FragmentHistoricoBinding.inflate(inflater, container, false);
        transacoes = new ArrayList<>();

        recyclerView = historicoBinding.recyclerViewTransacoes;
        adapter = new TransacaoAdapter(transacoes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listarTransacoes();

        return historicoBinding.getRoot();
    }

    private void listarTransacoes() {
        transacaoRepository.listarTodas(new OnDataListener<List<Transacao>>() {
            @Override
            public void onSuccess(List<Transacao> transacoes) {
                // Atualizar o RecyclerView com as transações obtidas
                adapter.setTransacoes(transacoes);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getContext(), "Erro ao obter transações", Toast.LENGTH_SHORT).show();
            }
        });
    }
}