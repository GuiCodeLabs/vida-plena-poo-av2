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
        validarDadosComplementares(idade, telefone);
        cadastrarPaciente(new Paciente(nome, cpf, idade, telefone));
    }

    public void cadastrarPaciente(String nome, String cpf, int idade, String telefone, Convenio convenio)
            throws OperacaoInvalidaException {
        validarDadosComplementares(idade, telefone);
        validarConvenioObrigatorio(convenio);
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
        validarDadosComplementares(idade, telefone);
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.complementar(idade, telefone);
    }

    public void complementarPaciente(String cpf, int idade, String telefone, String convenioNome)
            throws PacienteNaoEncontradoException, OperacaoInvalidaException {
        validarDadosComplementares(idade, telefone);
        validarTextoObrigatorio(convenioNome, "Convenio obrigatorio.");
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.complementar(idade, telefone, convenioNome);
    }

    public void complementarPaciente(String cpf, int idade, String telefone, Convenio convenio)
            throws PacienteNaoEncontradoException, OperacaoInvalidaException {
        validarDadosComplementares(idade, telefone);
        validarConvenioObrigatorio(convenio);
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
        return extrairDigitos(cpf);
    }

    private void validarPacienteParaCadastro(Paciente paciente) throws OperacaoInvalidaException {
        if (paciente == null) {
            throw new OperacaoInvalidaException("Paciente invalido.");
        }
        validarTextoObrigatorio(paciente.getNome(), "Nome obrigatorio.");
        String cpf = normalizarCpf(paciente.getCpf());
        validarCpf(cpf);
        if (cpfsCadastrados.contains(cpf)) {
            throw new OperacaoInvalidaException("CPF ja cadastrado.");
        }
    }

    private void validarDadosComplementares(int idade, String telefone) throws OperacaoInvalidaException {
        if (idade < 0) {
            throw new OperacaoInvalidaException("Idade invalida.");
        }
        if (idade > 130) {
            throw new OperacaoInvalidaException("Idade acima do limite permitido.");
        }
        validarTextoObrigatorio(telefone, "Telefone obrigatorio para complementar cadastro.");
        String telefoneTratado = extrairDigitos(telefone);
        if (telefoneTratado.length() < 8 || telefoneTratado.length() > 11) {
            throw new OperacaoInvalidaException("Telefone deve conter entre 8 e 11 digitos.");
        }
    }

    private void validarConvenioObrigatorio(Convenio convenio) throws OperacaoInvalidaException {
        if (convenio == null || convenio.getNome().trim().equals("")) {
            throw new OperacaoInvalidaException("Convenio obrigatorio.");
        }
    }

    private void validarTextoObrigatorio(String valor, String mensagem) throws OperacaoInvalidaException {
        if (valor == null || valor.trim().equals("")) {
            throw new OperacaoInvalidaException(mensagem);
        }
    }

    private void validarCpf(String cpf) throws OperacaoInvalidaException {
        if (cpf.equals("")) {
            throw new OperacaoInvalidaException("CPF obrigatorio.");
        }
        if (cpf.length() != 11) {
            throw new OperacaoInvalidaException("CPF deve conter 11 digitos.");
        }
        if (cpfTemDigitosRepetidos(cpf) || !cpfPossuiDigitosValidos(cpf)) {
            throw new OperacaoInvalidaException("CPF invalido.");
        }
    }

    private boolean cpfTemDigitosRepetidos(String cpf) {
        char primeiroDigito = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiroDigito) {
                return false;
            }
        }
        return true;
    }

    private boolean cpfPossuiDigitosValidos(String cpf) {
        int primeiroDigito = calcularDigitoCpf(cpf, 9);
        int segundoDigito = calcularDigitoCpf(cpf, 10);
        return primeiroDigito == Character.getNumericValue(cpf.charAt(9))
                && segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigitoCpf(String cpf, int quantidadeDigitos) {
        int soma = 0;
        int peso = quantidadeDigitos + 1;
        for (int i = 0; i < quantidadeDigitos; i++) {
            soma = soma + Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }
        int resto = soma % 11;
        if (resto < 2) {
            return 0;
        }
        return 11 - resto;
    }

    private String extrairDigitos(String valor) {
        if (valor == null) {
            return "";
        }
        String digitos = "";
        for (int i = 0; i < valor.length(); i++) {
            char caractere = valor.charAt(i);
            if (Character.isDigit(caractere)) {
                digitos = digitos + caractere;
            }
        }
        return digitos;
    }
}
