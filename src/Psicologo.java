import java.util.ArrayList;

public class Psicologo extends Profissional {
    private String abordagem;

    public Psicologo(String nome) {
        super(nome, "psicologia");
        setAbordagem("");
    }

    public Psicologo(String nome, String registroProfissional, double valorConsulta,
                     ArrayList<HorarioDisponivel> horarios, String abordagem) {
        super(nome, "psicologia", registroProfissional, valorConsulta, horarios);
        setAbordagem(abordagem);
    }

    public String getAbordagem() {
        return abordagem;
    }

    public void setAbordagem(String abordagem) {
        this.abordagem = abordagem;
    }

    public String exibirResumo() {
        return super.exibirResumo() + " | Abordagem: " + abordagem;
    }
}
