import java.util.ArrayList;

public class Prontuario {
    private String observacoes;
    private String diagnostico;
    private ArrayList<String> procedimentosRealizados;
    private String dataRegistro;

    public Prontuario() {
        this.procedimentosRealizados = new ArrayList<String>();
    }

    public Prontuario(String observacoes, String diagnostico, String dataRegistro) {
        this.procedimentosRealizados = new ArrayList<String>();
        setObservacoes(observacoes);
        setDiagnostico(diagnostico);
        setDataRegistro(dataRegistro);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        if (observacoes == null) {
            this.observacoes = "";
        } else {
            this.observacoes = observacoes.trim();
        }
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null) {
            this.diagnostico = "";
        } else {
            this.diagnostico = diagnostico.trim();
        }
    }

    public ArrayList<String> getProcedimentosRealizados() {
        return new ArrayList<String>(procedimentosRealizados);
    }

    public void setProcedimentosRealizados(ArrayList<String> procedimentosRealizados) {
        this.procedimentosRealizados = new ArrayList<String>();

        if (procedimentosRealizados != null) {
            for (String procedimento : procedimentosRealizados) {
                adicionarProcedimento(procedimento);
            }
        }
    }

    public void adicionarProcedimento(String procedimento) {
        if (procedimento != null) {
            String procedimentoTratado = procedimento.trim();

            if (!procedimentoTratado.isEmpty()) {
                this.procedimentosRealizados.add(procedimentoTratado);
            }
        }
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        if (dataRegistro == null) {
            this.dataRegistro = "";
        } else {
            this.dataRegistro = dataRegistro.trim();
        }
    }
}
