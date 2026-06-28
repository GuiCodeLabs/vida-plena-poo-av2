public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(int indiceConsulta, double valorBase, String tipoPagamento, int parcelas)
            throws PagamentoInvalidoException {
        super(indiceConsulta, 0, tipoPagamento, parcelas);
        validarParcelas(parcelas);
        this.valorFinal = calcularValorFinal(valorBase);
    }

    public PagamentoCartao(int indiceConsulta, double valorFinalJaCalculado, String tipoPagamento, int parcelas, boolean valorJaCalculado)
            throws PagamentoInvalidoException {
        super(indiceConsulta, 0, tipoPagamento, parcelas);
        validarParcelas(parcelas);
        this.valorFinal = (valorFinalJaCalculado < 0) ? 0 : valorFinalJaCalculado;
    }

    private void validarParcelas(int parcelas) throws PagamentoInvalidoException {
        if (parcelas < 1 || parcelas > 6) {
            throw new PagamentoInvalidoException(
                "Numero de parcelas invalido: " + parcelas + ". Permitido entre 1 e 6.");
        }
    }

    @Override
    public double calcularValorFinal(double valorBase) {
        double valor = valorBase;
        if (parcelas > 3) {
            int parcelasExtras = parcelas - 3;
            double taxa = valorBase * 0.025 * parcelasExtras;
            valor = valorBase + taxa;
        }
        return (valor < 0) ? 0 : valor;
    }
}