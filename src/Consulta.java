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

    public Consulta(String cpfPaciente, String nomeProfissional, String data,String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
    }


    public Consulta(String cpfPaciente, String nomeProfissional,String data, String horario,String tipo, String status) {
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

    @Override
    public void cancelar() {
        this.status = "cancelada";
    }
// Sobrecarga do método cancelar
    public String cancelar(String motivo) {
        cancelar();
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() {
        this.status = "remarcada";
    }

    public void realizar() {
        this.status = "realizada";
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