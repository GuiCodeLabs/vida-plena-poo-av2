import java.util.ArrayList;
import java.util.HashSet;

public class ClinicaServico {
    private ArrayList<Paciente> pacientes;
    private HashSet<String> cpfsCadastrados;

    public ClinicaServico() {
        pacientes = new ArrayList<Paciente>();
        cpfsCadastrados = new HashSet<String>();
    }

    public void cadastrarPaciente(Paciente paciente) {
        if (paciente == null) {
            return;
        }

        String cpf = normalizarCpf(paciente.getCpf());
        if (cpf.equals("") || cpfsCadastrados.contains(cpf)) {
            return;
        }

        pacientes.add(paciente);
        cpfsCadastrados.add(cpf);
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
        return cpfsCadastrados.contains(normalizarCpf(cpf));
    }

    public int getTotalPacientes() {
        return pacientes.size();
    }

    private int buscarIndicePaciente(String cpf) {
        String cpfBusca = normalizarCpf(cpf);
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getCpf().equals(cpfBusca)) {
                return i;
            }
        }
        return -1;
    }

    private String normalizarCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.trim();
    }
}
