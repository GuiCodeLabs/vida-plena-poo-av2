public class HorarioDisponivel {
    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana == null || diaSemana.trim().isEmpty()) {
            throw new IllegalArgumentException("Dia da semana nao pode ser vazio.");
        }
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            throw new IllegalArgumentException("Turno nao pode ser vazio.");
        }
        this.turno = turno;
    }

    public boolean atendeNoDia(String dia) {
        return diaSemana.equals(dia);
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }
}
