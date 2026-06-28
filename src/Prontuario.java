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
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.dataRegistro = dataRegistro;
        this.procedimentosRealizados = new ArrayList<String>();
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ArrayList<String> getProcedimentosRealizados() {
        return procedimentosRealizados;
    }

    public void setProcedimentosRealizados(ArrayList<String> procedimentosRealizados) {
        this.procedimentosRealizados = procedimentosRealizados;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
