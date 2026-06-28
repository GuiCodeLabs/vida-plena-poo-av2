public class Consulta implements Agendavel {

    public String cpfPaciente;
    public String nomeProfissional;
    public String data;
    public String horario;
    public String tipo;
    public String status;

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this(cpfPaciente, nomeProfissional, data, horario, "inicial");
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    @Override
    public void agendar() {
        this.status = "agendada";
    }

    // Sobrecarga
    public void agendar(String data, String horario) {
        this.data = data;
        this.horario = horario;
        this.status = "agendada";
    }

    @Override
    public void cancelar() {
        if (status.equals("realizada")) {
            System.out.println("Não é possível cancelar uma consulta já realizada.");
            return;
        }

        if (status.equals("cancelada")) {
            System.out.println("A consulta já está cancelada.");
            return;
        }

        status = "cancelada";
    }

    // Sobrecarga
    public String cancelar(String motivo) {
        cancelar();
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() {
        if (!status.equals("agendada")) {
            System.out.println("Apenas consultas agendadas podem ser remarcadas.");
            return;
        }

        status = "remarcada";
    }

    // Sobrecarga
    public void remarcar(String novaData, String novoHorario) {

        if (!status.equals("agendada")) {
            System.out.println("Apenas consultas agendadas podem ser remarcadas.");
            return;
        }

        this.data = novaData;
        this.horario = novoHorario;
        this.status = "remarcada";
    }

    public void realizar() {

        if (!status.equals("agendada")) {
            System.out.println("Apenas consultas agendadas podem ser realizadas.");
            return;
        }

        status = "realizada";
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente
                + " | Prof: " + nomeProfissional
                + " | Data: " + data
                + " | Hora: " + horario
                + " | Tipo: " + tipo
                + " | Status: " + status;
    }
}