public class Atendimento {
    public int indiceConsulta;
    public String observacoes;
    public String diagnostico;
    public String[] procedimentos;
    public int totalProcedimentos;
    private Consulta consulta;
    private Prontuario prontuario;

    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario();
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario();
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = totalProcedimentos;
        this.prontuario = new Prontuario();
        for (int i = 0; i < totalProcedimentos; i++) {
            this.procedimentos[i] = procedimentos[i];
        }
    }

    public Atendimento(Consulta consulta, String observacoes) {
        this(0, observacoes);
        this.consulta = consulta;
    }

    public Atendimento(Consulta consulta, String observacoes, String diagnostico) {
        this(0, observacoes, diagnostico);
        this.consulta = consulta;
    }

    public Atendimento(Consulta consulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this(0, observacoes, diagnostico, procedimentos, totalProcedimentos);
        this.consulta = consulta;
    }

    public void adicionarProcedimento(String procedimento) {
        if (totalProcedimentos < 10) {
            procedimentos[totalProcedimentos] = procedimento;
            totalProcedimentos++;
        }
    }

    public void adicionarProcedimento(String[] procs, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (totalProcedimentos < 10) {
                procedimentos[totalProcedimentos] = procs[i];
                totalProcedimentos++;
            }
        }
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico;
        }

        if (totalProcedimentos > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < totalProcedimentos; i++) {
                resumo = resumo + procedimentos[i];
                if (i < totalProcedimentos - 1) {
                    resumo = resumo + ", ";
                }
            }
        }
        return resumo;
    }
}
