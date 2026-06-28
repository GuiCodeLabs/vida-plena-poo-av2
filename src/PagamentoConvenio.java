/**
 * Classe PagamentoConvenio, subclasse de Pagamento.
 * Deve aplicar a taxa de cobertura percentual definida pelo Convenio.
 */
public class PagamentoConvenio extends Pagamento {
    public PagamentoConvenio() {
        super(0, 0.0, "convenio");
    }
    public PagamentoConvenio(int indiceConsulta, double valorFinal, String tipoPagamento) {
        super(indiceConsulta, valorFinal, tipoPagamento);
    }
}
