import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClinicaServico {
    private ArrayList<Paciente> pacientes;
    private HashMap<String, Paciente> pacientesPorCpf;
    private HashSet<String> cpfsCadastrados;

    public ClinicaServico() {
        pacientes = new ArrayList<Paciente>();
        pacientesPorCpf = new HashMap<String, Paciente>();
        cpfsCadastrados = new HashSet<String>();
    }

    public boolean cadastrarPaciente(Paciente paciente) {
        if (paciente == null) {
            return false;
        }

        String cpf = normalizarCpf(paciente.getCpf());
        if (cpf.equals("") || cpfsCadastrados.contains(cpf)) {
            return false;
        }

        pacientes.add(paciente);
        pacientesPorCpf.put(cpf, paciente);
        cpfsCadastrados.add(cpf);
        return true;
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        return pacientesPorCpf.get(normalizarCpf(cpf));
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
        return pacientesPorCpf.containsKey(normalizarCpf(cpf));
    }

    public int getTotalPacientes() {
        return pacientes.size();
    }

    private String normalizarCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.trim();
    }
}
