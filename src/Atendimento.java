import java.util.ArrayList;

public class Atendimento implements Exportavel {
    public int indiceConsulta;
    public String observacoes;
    public String diagnostico;
    public String[] procedimentos;
    public int totalProcedimentos;
    private Consulta consulta;
    private Prontuario prontuario;
    private boolean finalizado;

    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario();
        this.finalizado = false;
        registrarDadosClinicos(observacoes, "");
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario();
        this.finalizado = false;
        registrarDadosClinicos(observacoes, diagnostico);
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario();
        this.finalizado = false;
        registrarDadosClinicos(observacoes, diagnostico);

        if (procedimentos != null) {
            int limite = totalProcedimentos;

            if (limite > procedimentos.length) {
                limite = procedimentos.length;
            }

            for (int i = 0; i < limite; i++) {
                adicionarProcedimento(procedimentos[i]);
            }
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
        if (procedimento == null) {
            return;
        }

        String procedimentoTratado = procedimento.trim();

        if (procedimentoTratado.equals("")) {
            return;
        }

        prontuario.adicionarProcedimento(procedimentoTratado);

        if (procedimentos == null) {
            procedimentos = new String[10];
        }

        if (totalProcedimentos < 0) {
            totalProcedimentos = 0;
        }

        if (totalProcedimentos < procedimentos.length) {
            procedimentos[totalProcedimentos] = procedimentoTratado;
            totalProcedimentos++;
        }
    }

    public void adicionarProcedimento(String[] procs, int quantidade) {
        if (procs == null) {
            return;
        }

        int limite = quantidade;

        if (limite > procs.length) {
            limite = procs.length;
        }

        for (int i = 0; i < limite; i++) {
            adicionarProcedimento(procs[i]);
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

    public void finalizarAtendimento() {
        finalizado = true;

        if (consulta != null) {
            consulta.realizar();
        }
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    @Override
    public String exportarDados() {
        String dados = gerarResumoClinico();

        if (finalizado) {
            dados = dados + "\nAtendimento finalizado: sim";
        } else {
            dados = dados + "\nAtendimento finalizado: nao";
        }

        return dados;
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

        ArrayList<String> procedimentosProntuario = new ArrayList<String>();

        if (prontuario != null) {
            procedimentosProntuario = prontuario.getProcedimentosRealizados();
        }

        if (!procedimentosProntuario.isEmpty()) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < procedimentosProntuario.size(); i++) {
                resumo = resumo + procedimentosProntuario.get(i);
                if (i < procedimentosProntuario.size() - 1) {
                    resumo = resumo + ", ";
                }
            }
        } else if (procedimentos != null && totalProcedimentos > 0) {
            String procedimentosTexto = "";
            int limite = totalProcedimentos;

            if (limite > procedimentos.length) {
                limite = procedimentos.length;
            }

            for (int i = 0; i < limite; i++) {
                if (procedimentos[i] != null && !procedimentos[i].trim().equals("")) {
                    if (!procedimentosTexto.equals("")) {
                        procedimentosTexto = procedimentosTexto + ", ";
                    }
                    procedimentosTexto = procedimentosTexto + procedimentos[i].trim();
                }
            }

            if (!procedimentosTexto.equals("")) {
                resumo = resumo + "\nProcedimentos: " + procedimentosTexto;
            }
        }

        return resumo;
    }

    public String exibirResumo() {
        String resumo = "Observacoes: ";

        if (observacoes != null) {
            resumo = resumo + observacoes.trim();
        }

        if (diagnostico != null && !diagnostico.trim().equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico.trim();
        }

        if (procedimentos != null && totalProcedimentos > 0) {
            String procedimentosTexto = "";
            int limite = totalProcedimentos;

            if (limite > procedimentos.length) {
                limite = procedimentos.length;
            }

            for (int i = 0; i < limite; i++) {
                if (procedimentos[i] != null && !procedimentos[i].trim().equals("")) {
                    if (!procedimentosTexto.equals("")) {
                        procedimentosTexto = procedimentosTexto + ", ";
                    }
                    procedimentosTexto = procedimentosTexto + procedimentos[i].trim();
                }
            }

            if (!procedimentosTexto.equals("")) {
                resumo = resumo + "\nProcedimentos: " + procedimentosTexto;
            }
        }
        return resumo;
    }
}
