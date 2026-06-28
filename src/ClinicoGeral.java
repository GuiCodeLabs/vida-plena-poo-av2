import java.util.ArrayList;

public class ClinicoGeral extends Profissional {
    private String encaminhamento;

    public ClinicoGeral(String nome) {
        super(nome, "clinica geral");
        setEncaminhamento("");
    }

    public ClinicoGeral(String nome, String registroProfissional, double valorConsulta,
                        ArrayList<HorarioDisponivel> horarios, String encaminhamento) {
        super(nome, "clinica geral", registroProfissional, valorConsulta, horarios);
        setEncaminhamento(encaminhamento);
    }

    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public String exibirResumo() {
        return super.exibirResumo() + " | Encaminhamento: " + encaminhamento;
    }
}
