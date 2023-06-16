package vieira.ester.windstock;

public class Transacao {

    private String codigoItem;
    private String descricao;
    private String data;
    private String tipo;
    private int qtdItens;

    public Transacao() {
    }

    public Transacao(String codigoItem, String descricao, String data, String tipo, int qtdItens) {
        this.codigoItem = codigoItem;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
        this.qtdItens = qtdItens;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQtdItens() {
        return qtdItens;
    }
}
