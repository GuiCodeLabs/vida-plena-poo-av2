import java.util.ArrayList;

public class Fisioterapeuta extends Profissional {
    private int totalSessoesPrevistas;

    public Fisioterapeuta(String nome) {
        super(nome, "fisioterapia");
        setTotalSessoesPrevistas(0);
    }

    public Fisioterapeuta(String nome, String registroProfissional, double valorConsulta,
                          ArrayList<HorarioDisponivel> horarios, int totalSessoesPrevistas) {
        super(nome, "fisioterapia", registroProfissional, valorConsulta, horarios);
        setTotalSessoesPrevistas(totalSessoesPrevistas);
    }

    public int getTotalSessoesPrevistas() {
        return totalSessoesPrevistas;
    }

    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) {
        if (totalSessoesPrevistas < 0) {
            throw new IllegalArgumentException("Total de sessoes nao pode ser negativo.");
        }
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    public String exibirResumo() {
        return super.exibirResumo() + " | Sessoes previstas: " + totalSessoesPrevistas;
    }
}
