public class ClinicaServico {
    private Paciente[] pacientes;
    private int totalPacientes;

    public ClinicaServico() {
        pacientes = new Paciente[100];
        totalPacientes = 0;
    }

    public void cadastrarPaciente(Paciente paciente) {
        if (paciente != null && totalPacientes < pacientes.length) {
            pacientes[totalPacientes] = paciente;
            totalPacientes++;
        }
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        int indice = buscarIndicePaciente(cpf);
        if (indice == -1) {
            return null;
        }
        return pacientes[indice];
    }

    public Paciente[] listarPacientes() {
        Paciente[] resultado = new Paciente[totalPacientes];
        for (int i = 0; i < totalPacientes; i++) {
            resultado[i] = pacientes[i];
        }
        return resultado;
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
        return totalPacientes;
    }

    private int buscarIndicePaciente(String cpf) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
}
