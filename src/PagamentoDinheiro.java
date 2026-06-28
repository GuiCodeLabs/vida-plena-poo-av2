public class PagamentoDinheiro extends Pagamento {
    public PagamentoDinheiro() {
        super(0, 0.0, "dinheiro");
    }

    // SOBRECARGA 1/2: construtor com 3 parametros.
    // Usado no pagamento DIRETO, quando o usuario digita o valor base na hora
    // e o desconto de 5% ainda precisa ser calculado por este construtor.
    public PagamentoDinheiro(int indiceConsulta, double valorBase, String tipoPagamento) {
        super(indiceConsulta, 0, tipoPagamento, 1);
        this.valorFinal = calcularValorFinal(valorBase);
    }
    // SOBRECARGA 2/2: mesmo nome do construtor acima, mas com 1 parametro extra
    // (boolean). O compilador decide qual dos dois chamar de acordo com a
    // quantidade/tipo dos argumentos passados (resolvido em tempo de compilacao).
    // Usado no pagamento AUTOMATICO, quando o valor final ja vem pronto
    // (com desconto de convenio/retorno e multa ja aplicados em Main),
    // entao aqui so guardamos o valor, sem recalcular o desconto de 5%.
    public PagamentoDinheiro(int indiceConsulta, double valorFinalJaCalculado, String tipoPagamento, boolean valorJaCalculado) {
        super(indiceConsulta, 0, tipoPagamento, 1);
        if (valorJaCalculado) {
            this.valorFinal = (valorFinalJaCalculado < 0) ? 0 : valorFinalJaCalculado;
        } else {
            this.valorFinal = calcularValorFinal(valorFinalJaCalculado);
        }
    }
    // SOBRESCRITA: mesmo nome e mesma assinatura de Pagamento.calcularValorFinal(double).
    // PagamentoDinheiro redefine o comportamento aplicando 5% de desconto,
    // que e a regra especifica de pagamento em dinheiro/pix (jornada 21).
    // Resolvido em tempo de EXECUCAO: e o que chamamos de ligacao dinamica.
    @Override
    public double calcularValorFinal(double valorBase) {
        double desconto = valorBase * 0.05;
        double valor = valorBase - desconto;
        return (valor < 0) ? 0 : valor;
    }
}
