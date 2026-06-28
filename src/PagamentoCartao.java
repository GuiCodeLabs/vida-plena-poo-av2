/**
 * Classe PagamentoCartao, subclasse de Pagamento.
 * Deve aplicar taxas de 2,5% por parcela extra acima de 3 parcelas (limite 6x).
 */
public class PagamentoCartao extends Pagamento {
    public PagamentoCartao() {
        super(0, 0.0, "cartao");
    }
    public PagamentoCartao(int indiceConsulta, double valorFinal, String tipoPagamento, int parcelas) {
        super(indiceConsulta, valorFinal, tipoPagamento, parcelas);
    }
}
