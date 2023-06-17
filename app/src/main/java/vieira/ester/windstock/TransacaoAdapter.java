package vieira.ester.windstock;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vieira.ester.windstock.databinding.ItemTransacaoBinding;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> {
    private List<Transacao> transacoes;

    public TransacaoAdapter(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @NonNull
    @Override
    public TransacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTransacaoBinding adapterBinding = ItemTransacaoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TransacaoViewHolder(adapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoViewHolder holder, int position) {
        Transacao transacao = transacoes.get(position);
        holder.bind(transacao);
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
        notifyDataSetChanged();
    }


    public class TransacaoViewHolder extends RecyclerView.ViewHolder {
        private ItemTransacaoBinding adapterBinding;

        public TransacaoViewHolder(ItemTransacaoBinding adapterBinding) {
            super(adapterBinding.getRoot());
            this.adapterBinding = adapterBinding;
        }

        public void bind(Transacao transacao) {
            // Definir os valores adequados para cada transação nos TextViews usando o binding
            adapterBinding.textViewCodigoItem.setText(transacao.getCodigoItem());
            adapterBinding.textViewDescricao.setText(transacao.getDescricao());
            adapterBinding.textViewQuantidade.setText(String.valueOf(transacao.getQtdItens()));
        }
    }
}
