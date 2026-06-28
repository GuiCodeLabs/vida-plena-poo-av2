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

    public void cadastrarPaciente(String nome, String cpf) throws OperacaoInvalidaException {
        cadastrarPaciente(new Paciente(nome, cpf));
    }

    public void cadastrarPaciente(String nome, String cpf, int idade, String telefone)
            throws OperacaoInvalidaException {
        cadastrarPaciente(new Paciente(nome, cpf, idade, telefone));
    }

    public void cadastrarPaciente(String nome, String cpf, int idade, String telefone, Convenio convenio)
            throws OperacaoInvalidaException {
        cadastrarPaciente(new Paciente(nome, cpf, idade, telefone, convenio));
    }

    public void cadastrarPaciente(Paciente paciente) throws OperacaoInvalidaException {
        validarPacienteParaCadastro(paciente);
        String cpf = normalizarCpf(paciente.getCpf());
        pacientes.add(paciente);
        pacientesPorCpf.put(cpf, paciente);
        cpfsCadastrados.add(cpf);
    }

    public Paciente buscarPacientePorCpf(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = pacientesPorCpf.get(normalizarCpf(cpf));
        if (paciente == null) {
            throw new PacienteNaoEncontradoException("Paciente nao encontrado.");
        }
        return paciente;
    }

    public Paciente buscarPacienteAtivo(String cpf) throws PacienteNaoEncontradoException, PacienteInativoException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente inativo. Nao e possivel continuar a operacao.");
        }
        return paciente;
    }

    public Paciente[] listarPacientes() {
        return pacientes.toArray(new Paciente[pacientes.size()]);
    }

    public void desativarPaciente(String cpf) throws PacienteNaoEncontradoException, PacienteInativoException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente ja esta inativo.");
        }
        paciente.desativar();
    }

    public void complementarPaciente(String cpf, int idade, String telefone)
            throws PacienteNaoEncontradoException, OperacaoInvalidaException {
        validarComplementacao(idade, telefone);
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.complementar(idade, telefone);
    }

    public void complementarPaciente(String cpf, int idade, String telefone, String convenioNome)
            throws PacienteNaoEncontradoException, OperacaoInvalidaException {
        validarComplementacao(idade, telefone);
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.complementar(idade, telefone, convenioNome);
    }

    public void complementarPaciente(String cpf, int idade, String telefone, Convenio convenio)
            throws PacienteNaoEncontradoException, OperacaoInvalidaException {
        validarComplementacao(idade, telefone);
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.complementar(idade, telefone, convenio);
    }

    public boolean pacienteExiste(String cpf) {
        return pacientesPorCpf.containsKey(normalizarCpf(cpf));
    }

    public boolean pacienteTemConvenio(String cpf) throws PacienteNaoEncontradoException {
        return buscarPacientePorCpf(cpf).temConvenio();
    }

    private String normalizarCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.trim();
    }

    private void validarPacienteParaCadastro(Paciente paciente) throws OperacaoInvalidaException {
        if (paciente == null) {
            throw new OperacaoInvalidaException("Paciente invalido.");
        }
        validarTextoObrigatorio(paciente.getNome(), "Nome obrigatorio.");
        String cpf = normalizarCpf(paciente.getCpf());
        if (cpf.equals("")) {
            throw new OperacaoInvalidaException("CPF obrigatorio.");
        }
        if (cpfsCadastrados.contains(cpf)) {
            throw new OperacaoInvalidaException("CPF ja cadastrado.");
        }
    }

    private void validarComplementacao(int idade, String telefone) throws OperacaoInvalidaException {
        if (idade < 0) {
            throw new OperacaoInvalidaException("Idade invalida.");
        }
        validarTextoObrigatorio(telefone, "Telefone obrigatorio para complementar cadastro.");
    }

    private void validarTextoObrigatorio(String valor, String mensagem) throws OperacaoInvalidaException {
        if (valor == null || valor.trim().equals("")) {
            throw new OperacaoInvalidaException(mensagem);
        }
    }
}
