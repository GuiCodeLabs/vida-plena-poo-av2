public class PagamentoConvenio extends Pagamento {

    private Convenio convenio;

    // Construtor unico (ainda nao ha sobrecarga aqui porque o pagamento
    // automatico tambem precisa validar cobertura antes de criar o objeto,
    // entao os dois fluxos passam pelo mesmo caminho de validacao).
    public PagamentoConvenio(int indiceConsulta, double valorBase, String tipoPagamento,
                              Convenio convenio, String especialidadeConsulta)
            throws ConvenioNaoCobreException {
        super(indiceConsulta, 0, tipoPagamento, 1);

        if (convenio == null || !convenio.cobreEspecialidade(especialidadeConsulta)) {
            throw new ConvenioNaoCobreException(
                "O convenio nao cobre a especialidade: " + especialidadeConsulta);
        }

        this.convenio = convenio;
        this.valorFinal = calcularValorFinal(valorBase);
    }

    // SOBRESCRITA: mesmo nome e mesma assinatura de Pagamento.calcularValorFinal(double).
    // PagamentoConvenio redefine o comportamento aplicando o percentual de
    // cobertura do convenio sobre o valor base — regra especifica de
    // pagamento por convenio (jornada 23). Resolvido em tempo de EXECUCAO.
    @Override
    public double calcularValorFinal(double valorBase) {
        double percentualPagoPeloPaciente = 100 - convenio.getPercentualCobertura();
        double valor = valorBase * percentualPagoPeloPaciente / 100;
        return (valor < 0) ? 0 : valor;
    }

    public String getNomeConvenio() {
        return convenio.getNome();
    }
}