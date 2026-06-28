public abstract class Pagamento {
    protected int indiceConsulta;
    protected double valorFinal;
    protected String tipoPagamento;
    protected int parcelas;
    protected String statusPagamento;

    public Pagamento(int indiceConsulta, double valorFinal, String tipoPagamento, int parcelas) {
        this.indiceConsulta = indiceConsulta;
        this.valorFinal = valorFinal;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
        this.statusPagamento = "Pendente";
    }

    public abstract double calcularValorFinal(double valorBase);

    public String exibirResumo() {
        double valorArredondado = Math.round(valorFinal * 100.0) / 100.0;
        String resumo = "Consulta #" + indiceConsulta + " | Valor: R$" + valorArredondado
                + " | Tipo: " + tipoPagamento + " | Parcelas: " + parcelas
                + " | Status: " + statusPagamento;
        if (parcelas > 1) {
            double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
            resumo = resumo + " (R$" + valorParcela + " cada)";
        }
        return resumo;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public int getIndiceConsulta() {
        return indiceConsulta;
    }
}