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
            throw new IllegalArgumentException("Data disponível do horário nao pode ser vazia.");
        }
        this.diaSemana = normalizeDay(diaSemana);
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            throw new IllegalArgumentException("Turno nao pode ser vazio.");
        }
        this.turno = normalizeTurno(turno);
    }

    public boolean atendeNoDia(String dia) {
        if (dia == null) {
            return false;
        }
        return diaSemana.equals(normalizeDay(dia));
    }

    public boolean atendeNoHorario(String dia, String turno) {
        if (dia == null || turno == null) {
            return false;
        }
        return diaSemana.equals(normalizeDay(dia)) && turno.equals(normalizeTurno(turno));
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }

    private String normalizeDay(String dia) {
        if (dia == null) {
            return "";
        }
        String texto = dia.trim();
        if (texto.length() != 10 || texto.charAt(2) != '/' || texto.charAt(5) != '/') {
            throw new IllegalArgumentException("Formato de data inválido. Use DD/MM/AAAA.");
        }
        for (int i = 0; i < texto.length(); i++) {
            if (i == 2 || i == 5) continue;
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("Formato de data inválido. Use DD/MM/AAAA.");
            }
        }
        return texto;
    }

    private String normalizeTurno(String turno) {
        if (turno == null) {
            return "";
        }
        String texto = turno.trim().toLowerCase();
        texto = texto.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
        texto = texto.replace("-", " ");
        if (texto.contains("manha") || texto.contains("matutino")) return "manha";
        if (texto.contains("tarde")) return "tarde";
        if (texto.contains("noite")) return "noite";
        return texto;
    }
}
