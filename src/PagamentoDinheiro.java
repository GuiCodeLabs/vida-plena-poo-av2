/**
 * Classe PagamentoDinheiro, subclasse de Pagamento.
 * Deve aplicar 5% de desconto no valor base da consulta.
 */
public class PagamentoDinheiro extends Pagamento {
    public PagamentoDinheiro() {
        super(0, 0.0, "dinheiro");
    }
    public PagamentoDinheiro(int indiceConsulta, double valorFinal, String tipoPagamento) {
        super(indiceConsulta, valorFinal, tipoPagamento);
    }
}
