import java.util.ArrayList;

public class Nutricionista extends Profissional {
    private String planoAlimentar;

    public Nutricionista(String nome) {
        super(nome, "nutricao");
        setPlanoAlimentar("");
    }

    public Nutricionista(String nome, String registroProfissional, double valorConsulta,
                         ArrayList<HorarioDisponivel> horarios, String planoAlimentar) {
        super(nome, "nutricao", registroProfissional, valorConsulta, horarios);
        setPlanoAlimentar(planoAlimentar);
    }

    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }

    public String exibirResumo() {
        return super.exibirResumo() + " | Plano alimentar: " + planoAlimentar;
    }
}
