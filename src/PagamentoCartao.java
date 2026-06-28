public class PagamentoCartao extends Pagamento {
    public PagamentoCartao() {
        super(0, 0.0, "cartao", 1);
    }

    // SOBRECARGA 1/2: construtor com 4 parametros.
    // Usado no pagamento DIRETO: recebe o valor base e ainda precisa validar
    // as parcelas e calcular a taxa de parcelamento neste construtor.
    public PagamentoCartao(int indiceConsulta, double valorBase, String tipoPagamento, int parcelas)
            throws PagamentoInvalidoException {
        super(indiceConsulta, 0, tipoPagamento, parcelas);
        validarParcelas(parcelas);
        this.valorFinal = calcularValorFinal(valorBase);
    }

    // SOBRECARGA 2/2: mesmo nome do construtor acima, com 1 parametro extra
    // (boolean) ao final. O Java escolhe esta versao quando o valor final
    // ja vem calculado pelo pagamento AUTOMATICO (desconto de convenio/retorno
    // e multa ja aplicados em Main); aqui so validamos parcelas e guardamos o valor.
    public PagamentoCartao(int indiceConsulta, double valorFinalJaCalculado, String tipoPagamento, int parcelas, boolean valorJaCalculado)
            throws PagamentoInvalidoException {
        super(indiceConsulta, 0, tipoPagamento, parcelas);
        validarParcelas(parcelas);
        this.valorFinal = (valorFinalJaCalculado < 0) ? 0 : valorFinalJaCalculado;
    }

    // Validacao de negocio (jornada 30): impede parcelamento fora da regra da
    // clinica, lancando uma exception tratada em Main em vez de deixar o
    // sistema aceitar um valor invalido silenciosamente.
    private void validarParcelas(int parcelas) throws PagamentoInvalidoException {
        if (parcelas < 1 || parcelas > 6) {
            throw new PagamentoInvalidoException(
                "Numero de parcelas invalido: " + parcelas + ". Permitido entre 1 e 6.");
        }
    }

    // SOBRESCRITA: mesmo nome e mesma assinatura de Pagamento.calcularValorFinal(double).
    // PagamentoCartao redefine o comportamento aplicando taxa de 2,5% por
    // parcela acima da 3a, que e a regra especifica do cartao (jornada 22).
    // Resolvido em tempo de EXECUCAO (ligacao dinamica/polimorfismo).
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
