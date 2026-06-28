import java.util.ArrayList;

public class ClinicaServico {
    private ArrayList<Paciente> pacientes;

    public ClinicaServico() {
        pacientes = new ArrayList<Paciente>();
    }

    public void cadastrarPaciente(Paciente paciente) {
        if (paciente != null) {
            pacientes.add(paciente);
        }
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        int indice = buscarIndicePaciente(cpf);
        if (indice == -1) {
            return null;
        }
        return pacientes.get(indice);
    }

    public Paciente[] listarPacientes() {
        return pacientes.toArray(new Paciente[pacientes.size()]);
    }

    public void desativarPaciente(String cpf) {
        Paciente paciente = buscarPacientePorCpf(cpf);
        if (paciente != null) {
            paciente.desativar();
        }
    }

    public boolean pacienteExiste(String cpf) {
        return buscarIndicePaciente(cpf) != -1;
    }

    public int getTotalPacientes() {
        return pacientes.size();
    }

    private int buscarIndicePaciente(String cpf) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
}
