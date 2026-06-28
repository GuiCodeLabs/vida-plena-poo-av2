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
        prontuario.adicionarProcedimento(procedimento);

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

    public void registrarDadosClinicos(String observacoes, String diagnostico) {
        prontuario.setObservacoes(observacoes);
        prontuario.setDiagnostico(diagnostico);
        this.observacoes = prontuario.getObservacoes();
        this.diagnostico = prontuario.getDiagnostico();
    }

    public String gerarResumoClinico() {
        String resumo = "Resumo clinico do atendimento";

        if (consulta != null) {
            resumo = resumo + "\nConsulta: " + consulta.exibirResumo();
        } else {
            resumo = resumo + "\nConsulta: " + indiceConsulta;
        }

        if (observacoes != null && !observacoes.trim().equals("")) {
            resumo = resumo + "\nObservacoes: " + observacoes.trim();
        } else if (prontuario != null && prontuario.getObservacoes() != null
                && !prontuario.getObservacoes().equals("")) {
            resumo = resumo + "\nObservacoes: " + prontuario.getObservacoes();
        }

        if (diagnostico != null && !diagnostico.trim().equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico.trim();
        } else if (prontuario != null && prontuario.getDiagnostico() != null
                && !prontuario.getDiagnostico().equals("")) {
            resumo = resumo + "\nDiagnostico: " + prontuario.getDiagnostico();
        }

        if (prontuario != null && prontuario.getDataRegistro() != null
                && !prontuario.getDataRegistro().equals("")) {
            resumo = resumo + "\nData do registro: " + prontuario.getDataRegistro();
        }

        if (prontuario != null && !prontuario.getProcedimentosRealizados().isEmpty()) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < prontuario.getProcedimentosRealizados().size(); i++) {
                resumo = resumo + prontuario.getProcedimentosRealizados().get(i);
                if (i < prontuario.getProcedimentosRealizados().size() - 1) {
                    resumo = resumo + ", ";
                }
            }
        } else if (totalProcedimentos > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < totalProcedimentos; i++) {
                if (procedimentos[i] != null) {
                    resumo = resumo + procedimentos[i];
                    if (i < totalProcedimentos - 1) {
                        resumo = resumo + ", ";
                    }
                }
            }
        }

        return resumo;
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
