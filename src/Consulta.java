// A classe Consulta implementa a interface Agendavel,
// garantindo que todas as consultas possuam operações
// básicas de agendamento, cancelamento e remarcação.
public class Consulta implements Agendavel {

    public String cpfPaciente;
    public String nomeProfissional;
    public String data;
    public String horario;
    public String tipo;
    public String status;

    // SOBRECARGA: mesmo nome (Consulta), parâmetros diferentes.
    // Resolvido em tempo de compilação.
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this(cpfPaciente, nomeProfissional, data, horario, "inicial");
    }

    // SOBRECARGA: construtor com parâmetro extra para informar o tipo da consulta.
    // Resolvido em tempo de compilação.
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
    }

    // SOBRECARGA: construtor com parâmetro extra para informar também o status.
    // Resolvido em tempo de compilação.
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    // SOBRESCRITA: mesmo nome e parâmetros do método da interface Agendavel.
    // A classe Consulta redefine o comportamento em tempo de execução.
    @Override
    public void agendar() {
        this.status = "agendada";
    }

    // SOBRECARGA: mesmo nome do método agendar, mas com parâmetros diferentes.
    // Resolvido em tempo de compilação.
    public void agendar(String data, String horario) {
        this.data = data;
        this.horario = horario;
        this.status = "agendada";
    }

    // SOBRESCRITA: implementação do método cancelar() definido em Agendavel.
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

    // SOBRECARGA: mesmo nome do método cancelar, mas recebe o motivo.
    // Resolvido em tempo de compilação.
    public String cancelar(String motivo) {
        cancelar();
        return "Consulta cancelada. Motivo: " + motivo;
    }

    // SOBRESCRITA: implementação do método remarcar() definido em Agendavel.
    @Override
    public void remarcar() {
        if (!status.equals("agendada")) {
            System.out.println("Apenas consultas agendadas podem ser remarcadas.");
            return;
        }

        status = "remarcada";
    }

    // SOBRECARGA: mesmo nome do método remarcar, mas recebe nova data e novo horário.
    // Resolvido em tempo de compilação.
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