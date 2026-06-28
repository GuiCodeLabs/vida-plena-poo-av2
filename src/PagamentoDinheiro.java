public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(int indiceConsulta, double valorBase, String tipoPagamento) {
        super(indiceConsulta, 0, tipoPagamento, 1);
        this.valorFinal = calcularValorFinal(valorBase);
    }

    public PagamentoDinheiro(int indiceConsulta, double valorFinalJaCalculado, String tipoPagamento, boolean valorJaCalculado) {
        super(indiceConsulta, 0, tipoPagamento, 1);
        if (valorJaCalculado) {
            this.valorFinal = (valorFinalJaCalculado < 0) ? 0 : valorFinalJaCalculado;
        } else {
            this.valorFinal = calcularValorFinal(valorFinalJaCalculado);
        }
    }

    @Override
    public double calcularValorFinal(double valorBase) {
        double desconto = valorBase * 0.05;
        double valor = valorBase - desconto;
        return (valor < 0) ? 0 : valor;
    }
}