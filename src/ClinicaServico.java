import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Serviço central da clínica: gerencia pacientes e auxilia nas operações de
 * agendamento/cancelamento/remarcação de consultas.
 */
public class ClinicaServico {

    // Localiza um paciente pelo CPF para validar operações envolvendo consultas.
    public static int buscarIndicePaciente(Paciente[] pacientes, int totalPacientes, String cpf) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
    // Gerenciamento de pacientes
    private ArrayList<Paciente> pacientes;
    private HashMap<String, Paciente> pacientesPorCpf;
    private HashSet<String> cpfsCadastrados;
    private ArrayList<Profissional> profissionais;
    private HashMap<String, Profissional> profissionaisPorNome;
    private ArrayList<Atendimento> atendimentos;

    public ClinicaServico() {
        pacientes = new ArrayList<Paciente>();
        pacientesPorCpf = new HashMap<String, Paciente>();
        cpfsCadastrados = new HashSet<String>();
        profissionais = new ArrayList<Profissional>();
        profissionaisPorNome = new HashMap<String, Profissional>();
        atendimentos = new ArrayList<Atendimento>();
    }

    public boolean especialidadeAceita(String especialidade) {
        return Profissional.especialidadeValida(especialidade);
    }

    public boolean cadastrarProfissional(String nome, String especialidade)
            throws OperacaoInvalidaException {
        return cadastrarProfissional(criarProfissional(nome, especialidade));
    }

    public boolean cadastrarProfissional(String nome, String especialidade,
                                         String registroProfissional, double valorConsulta,
                                         String dadoEspecifico)
            throws OperacaoInvalidaException {
        return cadastrarProfissional(
                criarProfissional(nome, especialidade, registroProfissional,
                        valorConsulta, new ArrayList<HorarioDisponivel>(), dadoEspecifico)
        );
    }

    public boolean cadastrarProfissional(String nome, String especialidade,
                                         String registroProfissional, double valorConsulta,
                                         ArrayList<HorarioDisponivel> horarios,
                                         String dadoEspecifico)
            throws OperacaoInvalidaException {
        return cadastrarProfissional(
                criarProfissional(nome, especialidade, registroProfissional,
                        valorConsulta, horarios, dadoEspecifico)
        );
    }

    private Profissional criarProfissional(String nome, String especialidade)
            throws OperacaoInvalidaException {
        validarNomeProfissional(nome);
        validarEspecialidade(especialidade);
        if (especialidade.equals("clinica geral")) {
            return new ClinicoGeral(nome);
        }
        if (especialidade.equals("fisioterapia")) {
            return new Fisioterapeuta(nome);
        }
        if (especialidade.equals("psicologia")) {
            return new Psicologo(nome);
        }
        if (especialidade.equals("nutricao")) {
            return new Nutricionista(nome);
        }
        throw new OperacaoInvalidaException("Especialidade invalida.");
    }

    private Profissional criarProfissional(String nome, String especialidade,
                                           String registroProfissional, double valorConsulta,
                                           ArrayList<HorarioDisponivel> horarios,
                                           String dadoEspecifico)
            throws OperacaoInvalidaException {
        validarNomeProfissional(nome);
        validarEspecialidade(especialidade);
        validarValorConsulta(valorConsulta);
        if (dadoEspecifico == null) {
            dadoEspecifico = "";
        }

        if (especialidade.equals("clinica geral")) {
            return new ClinicoGeral(nome, registroProfissional, valorConsulta, horarios, dadoEspecifico);
        }
        if (especialidade.equals("fisioterapia")) {
            int totalSessoes = 0;
            if (!dadoEspecifico.equals("")) {
                try {
                    totalSessoes = Integer.parseInt(dadoEspecifico);
                } catch (NumberFormatException e) {
                    throw new OperacaoInvalidaException("Total de sessoes deve ser um numero inteiro.", e);
                }
            }
            return new Fisioterapeuta(nome, registroProfissional, valorConsulta, horarios, totalSessoes);
        }
        if (especialidade.equals("psicologia")) {
            return new Psicologo(nome, registroProfissional, valorConsulta, horarios, dadoEspecifico);
        }
        if (especialidade.equals("nutricao")) {
            return new Nutricionista(nome, registroProfissional, valorConsulta, horarios, dadoEspecifico);
        }
        throw new OperacaoInvalidaException("Especialidade invalida.");
    }

    private boolean cadastrarProfissional(Profissional profissional)
            throws OperacaoInvalidaException {
        if (profissional == null) {
            throw new OperacaoInvalidaException("Profissional invalido.");
        }
        if (!especialidadeAceita(profissional.getEspecialidade())) {
            throw new OperacaoInvalidaException("Especialidade invalida.");
        }
        if (profissionaisPorNome.containsKey(profissional.getNome())) {
            throw new OperacaoInvalidaException("Ja existe profissional com esse nome.");
        }

        profissionais.add(profissional);
        profissionaisPorNome.put(profissional.getNome(), profissional);
        return true;
    }

    public boolean atualizarProfissional(String nome, String registro, double valor)
            throws ProfissionalNaoEncontradoException, OperacaoInvalidaException {
        validarValorConsulta(valor);
        Profissional profissional = buscarProfissionalPorNome(nome);
        profissional.atualizar(registro, valor);
        return true;
    }

    public boolean atualizarProfissional(String nome, String registro, double valor,
                                         ArrayList<HorarioDisponivel> horarios)
            throws ProfissionalNaoEncontradoException, OperacaoInvalidaException {
        validarValorConsulta(valor);
        Profissional profissional = buscarProfissionalPorNome(nome);
        profissional.atualizar(registro, valor, horarios);
        return true;
    }

    public Profissional buscarProfissionalPorNome(String nome)
            throws ProfissionalNaoEncontradoException {
        Profissional profissional = profissionaisPorNome.get(nome);
        if (profissional == null) {
            throw new ProfissionalNaoEncontradoException("Profissional nao encontrado.");
        }
        return profissional;
    }

    public ArrayList<Profissional> listarProfissionais() {
        return new ArrayList<Profissional>(profissionais);
    }

    public ArrayList<Profissional> filtrarProfissionaisPorEspecialidade(String especialidade) {
        ArrayList<Profissional> filtrados = new ArrayList<Profissional>();
        for (Profissional profissional : profissionais) {
            if (profissional.getEspecialidade().equals(especialidade)) {
                filtrados.add(profissional);
            }
        }
        return filtrados;
    }

    private void validarNomeProfissional(String nome) throws OperacaoInvalidaException {
        if (nome == null || nome.trim().equals("")) {
            throw new OperacaoInvalidaException("Nome do profissional nao pode ser vazio.");
        }
    }

    private void validarValorConsulta(double valorConsulta) throws OperacaoInvalidaException {
        if (valorConsulta < 0) {
            throw new OperacaoInvalidaException("Valor da consulta nao pode ser negativo.");
        }
    }

    private void validarEspecialidade(String especialidade) throws OperacaoInvalidaException {
        if (!especialidadeAceita(especialidade)) {
            throw new OperacaoInvalidaException("Especialidade invalida.");
        }
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


    // ----- Métodos de gerenciamento de consultas -----
    public boolean temConflito(ArrayList<Consulta> consultas, String nomeProf, String data, String horario) {
        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            if (consulta.nomeProfissional.equals(nomeProf)
                    && consulta.data.equals(data)
                    && consulta.horario.equals(horario)
                    && consulta.status.equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    public String sugerirHorario(ArrayList<Consulta> consultas, String nomeProf, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(consultas, nomeProf, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    private int buscarIndiceProfissional(Profissional[] profissionais, int totalProfissionais, String nome) {
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public void agendarConsultaPorProfissional(
            ArrayList<Consulta> consultas,
            Profissional[] profissionais,
            int totalProfissionais,
            String cpf,
            String nomeProf,
            String data,
            String horario,
            String tipo,
            String diaSemana
        ) throws ConsultaNaoEncontradaException, PacienteNaoEncontradoException, PacienteInativoException,
            ProfissionalNaoEncontradoException, HorarioIndisponivelException,
            OperacaoInvalidaException {

        Paciente paciente = buscarPacientePorCpf(cpf);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente inativo. Não é possível agendar consulta.");
        }

        int idxProf = buscarIndiceProfissional(profissionais, totalProfissionais, nomeProf);
        if (idxProf == -1) {
            throw new ProfissionalNaoEncontradoException("Profissional não encontrado.");
        }

        if (profissionais[idxProf].getValorConsulta() == 0) {
            throw new OperacaoInvalidaException("Profissional sem valor definido. Não pode agendar.");
        }

        if (!profissionais[idxProf].atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional não atende nesse dia.");
        }

        if (temConflito(consultas, nomeProf, data, horario)) {
            throw new HorarioIndisponivelException("Horário ocupado para esse profissional.");
        }

        if (tipo == null || tipo.equals("")) {
            consultas.add(new Consulta(cpf, nomeProf, data, horario));
        } else {
            consultas.add(new Consulta(cpf, nomeProf, data, horario, tipo));
        }
    }

    public void agendarConsultaPorEspecialidade(
            ArrayList<Consulta> consultas,
            Profissional[] profissionais,
            int totalProfissionais,
            String cpf,
            String especialidade,
            String data,
            String horario,
            String diaSemana
        ) throws ConsultaNaoEncontradaException, PacienteNaoEncontradoException, PacienteInativoException,
            HorarioIndisponivelException {

        Paciente paciente = buscarPacientePorCpf(cpf);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente inativo. Não é possível agendar consulta.");
        }

        int idxProf = -1;
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getEspecialidade().equals(especialidade)
                    && profissionais[i].getValorConsulta() > 0
                    && profissionais[i].atendeNoDia(diaSemana)
                    && !temConflito(consultas, profissionais[i].getNome(), data, horario)) {
                idxProf = i;
                break;
            }
        }

        if (idxProf == -1) {
            throw new HorarioIndisponivelException("Nenhum profissional disponível para essa especialidade.");
        }

        consultas.add(new Consulta(cpf, profissionais[idxProf].getNome(), data, horario));
    }

    public Consulta buscarConsulta(
            ArrayList<Consulta> consultas,
            String cpf,
            String data,
            String horario
    ) throws ConsultaNaoEncontradaException {

        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            if (consulta.cpfPaciente.equals(cpf)
                    && consulta.data.equals(data)
                    && consulta.horario.equals(horario)) {
                return consulta;
            }
        }

        throw new ConsultaNaoEncontradaException("Consulta não encontrada.");
    }

    public Atendimento registrarAtendimento(
            Consulta consulta,
            String observacoes,
            String diagnostico,
            String procedimentoInicial
    ) {
        if (consulta == null) {
            return null;
        }

        Atendimento atendimento = new Atendimento(consulta, observacoes, diagnostico);
        atendimento.registrarDadosClinicos(observacoes, diagnostico);

        if (procedimentoInicial != null && !procedimentoInicial.trim().equals("")) {
            atendimento.adicionarProcedimento(procedimentoInicial.trim());
        }

        atendimento.finalizarAtendimento();
        atendimentos.add(atendimento);

        return atendimento;
    }

    public Atendimento registrarAtendimento(
            Consulta consulta,
            String observacoes,
            String diagnostico,
            String procedimentoInicial,
            String registroEspecifico
    ) {
        Atendimento atendimento = registrarAtendimento(consulta, observacoes, diagnostico, procedimentoInicial);

        if (atendimento != null) {
            atendimento.setRegistroEspecifico(registroEspecifico);
        }

        return atendimento;
    }

    public void cancelarConsulta(
            ArrayList<Consulta> consultas,
            String cpf,
            String data,
            String horario,
            String motivo
    ) throws ConsultaNaoEncontradaException, OperacaoInvalidaException {

        Consulta consulta = buscarConsulta(consultas, cpf, data, horario);

        if (consulta.status.equals("realizada")) {
            throw new OperacaoInvalidaException("Consulta já realizada. Não pode cancelar.");
        }

        if (consulta.status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Consulta já está cancelada.");
        }

        if (motivo == null || motivo.equals("")) {
            consulta.cancelar();
        } else {
            consulta.cancelar(motivo);
        }
    }

    public void remarcarConsulta(
            ArrayList<Consulta> consultas,
            Profissional[] profissionais,
            int totalProfissionais,
            String cpf,
            String dataOrig,
            String horarioOrig,
            String novaData,
            String novoHorario,
            String diaSemana
    ) throws ConsultaNaoEncontradaException, HorarioIndisponivelException,
            OperacaoInvalidaException {

        Consulta consultaOriginal = buscarConsulta(consultas, cpf, dataOrig, horarioOrig);

        if (!consultaOriginal.status.equals("agendada")) {
            throw new OperacaoInvalidaException("Somente consultas agendadas podem ser remarcadas.");
        }

        String nomeProf = consultaOriginal.nomeProfissional;
        int idxProf = buscarIndiceProfissional(profissionais, totalProfissionais, nomeProf);

        if (idxProf == -1) {
            throw new ConsultaNaoEncontradaException("Profissional da consulta não encontrado.");
        }

        if (!profissionais[idxProf].atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional não atende nesse dia.");
        }

        if (temConflito(consultas, nomeProf, novaData, novoHorario)) {
            throw new HorarioIndisponivelException("Horário ocupado. Não foi possível remarcar.");
        }

        String tipoConsulta = consultaOriginal.tipo;

        consultaOriginal.remarcar();

        Consulta novaConsulta = new Consulta(cpf, nomeProf, novaData, novoHorario, tipoConsulta);
        novaConsulta.agendar(novaData, novoHorario);

        consultas.add(novaConsulta);
    }

}
