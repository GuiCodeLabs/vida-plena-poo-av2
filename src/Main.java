import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ClinicaServico servico = new ClinicaServico();

    static Profissional[] profissionais = new Profissional[50];
    static int totalProfissionais = 0;

    static ArrayList<Consulta> consultas = new ArrayList<>();

    static Atendimento[] atendimentos = new Atendimento[200];
    static int totalAtendimentos = 0;

    static Pagamento[] pagamentos = new Pagamento[200];
    static int totalPagamentos = 0;

    static double[] multas = new double[100];
    static int totalMultas = 0;

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Convenio> conveniosCadastrados = criarConveniosTeste();

    static ArrayList<Convenio> criarConveniosTeste() {
        ArrayList<Convenio> lista = new ArrayList<Convenio>();
        lista.add(new Convenio("UnimedTeste", 60,
            new String[]{"clinica geral", "psicologia"}));
        lista.add(new Convenio("BradescoSaudeTeste", 40,
            new String[]{"fisioterapia", "nutricao"}));
        return lista;
    }

    static Convenio buscarConvenioPorNome(String nomeConvenio) {
        for (int i = 0; i < conveniosCadastrados.size(); i++) {
            if (conveniosCadastrados.get(i).getNome().equalsIgnoreCase(nomeConvenio)) {
                return conveniosCadastrados.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer opcao = -1;
        while (opcao.intValue() != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
        System.out.println("1 - Pacientes: cadastrar, buscar e listar");
        System.out.println("2 - Profissionais: cadastrar, atualizar e ver disponibilidade");
        System.out.println("3 - Consultas: agendar, cancelar e remarcar");
        System.out.println("4 - Atendimentos: registrar resultado de consulta");
        System.out.println("5 - Pagamentos: registrar e listar pagamentos");
        System.out.println("6 - Relatorios: gerar resumos e filtros");
        System.out.println("0 - Sair");
        opcao = lerInteiro("Escolha uma opcao: ");
        if (opcao == null) {
            opcao = -1;
            continue;
        }

            switch (opcao.intValue()) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    // ---- PACIENTES ----

    public static void menuPacientes() {
        Integer op = -1;
        while (op.intValue() != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            if (op == null) {
                op = -1;
                continue;
            }

            switch (op.intValue()) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarPaciente() {
        try {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            Integer tipo = lerInteiro("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
            if (tipo == null) {
                return;
            }

            if (tipo.intValue() == 1) {
                servico.cadastrarPaciente(nome, cpf);
            } else if (tipo.intValue() == 2) {
                Integer idade = lerInteiro("Idade: ");
                if (idade == null) {
                    return;
                }
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                servico.cadastrarPaciente(nome, cpf, idade.intValue(), tel);
            } else if (tipo.intValue() == 3) {
                Integer idade = lerInteiro("Idade: ");
                if (idade == null) {
                    return;
                }
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                System.out.print("Convenio: ");
                String conv = sc.nextLine();
                Convenio convenio = criarConvenio(conv);
                servico.cadastrarPaciente(nome, cpf, idade.intValue(), tel, convenio);
            } else {
                System.out.println("Tipo de cadastro invalido.");
                return;
            }
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (OperacaoInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("--- Operacao de paciente finalizada ---");
        }
    }

    public static void complementarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            Integer tipo = lerInteiro("Vai informar convenio? (1-Nao / 2-Sim): ");
            if (tipo == null) {
                return;
            }

            Integer idade = lerInteiro("Idade: ");
            if (idade == null) {
                return;
            }
            System.out.print("Telefone: ");
            String tel = sc.nextLine();

            if (tipo.intValue() == 1) {
                servico.complementarPaciente(cpf, idade.intValue(), tel);
            } else if (tipo.intValue() == 2) {
                System.out.print("Convenio: ");
                String conv = sc.nextLine();
                Convenio convenio = criarConvenio(conv);
                servico.complementarPaciente(cpf, idade.intValue(), tel, convenio);
            } else {
                System.out.println("Tipo de complementacao invalido.");
                return;
            }

            System.out.println("Cadastro atualizado!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("--- Operacao de paciente finalizada ---");
        }
    }

    public static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            Paciente paciente = servico.buscarPacientePorCpf(cpf);
            System.out.println(paciente.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("--- Operacao de paciente finalizada ---");
        }
    }

    public static void listarPacientes() {
        Paciente[] pacientesCadastrados = servico.listarPacientes();
        if (pacientesCadastrados.length == 0) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (int i = 0; i < pacientesCadastrados.length; i++) {
            System.out.println(pacientesCadastrados[i].exibirResumo());
        }
    }

    public static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            servico.desativarPaciente(cpf);
            System.out.println("Paciente desativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("--- Operacao de paciente finalizada ---");
        }
    }

    public static Integer lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor numerico invalido.");
            return null;
        }
    }

    public static boolean validarFormatoData(String data) {
        if (data == null || data.length() != 10) {
            return false;
        }
        if (data.charAt(2) != '/' || data.charAt(5) != '/') {
            return false;
        }
        for (int i = 0; i < data.length(); i++) {
            if (i == 2 || i == 5) continue;
            if (!Character.isDigit(data.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Convenio criarConvenio(String nomeConvenio) {
        if (nomeConvenio == null || nomeConvenio.trim().equals("")) {
            return null;
        }
        return new Convenio(nomeConvenio, 0);
    }

    // ---- PROFISSIONAIS ----

    public static void menuProfissionais() {
        Integer op = -1;
        while (op.intValue() != 0) {
            System.out.println("\n--- MENU DE PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar profissional");
            System.out.println("2 - Atualizar cadastro de profissional");
            System.out.println("3 - Listar todos os profissionais");
            System.out.println("4 - Filtrar profissionais por especialidade");
            System.out.println("0 - Voltar ao menu principal");
            op = lerInteiro("Escolha uma opcao: ");
            if (op == null) {
                op = -1;
                continue;
            }

            switch (op.intValue()) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarProfissional() {
        if (totalProfissionais >= profissionais.length) {
            System.out.println("Limite de profissionais atingido.");
            return;
        }

        System.out.println("--- Novo Profissional ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("CPF do profissional: ");
        String cpf = sc.nextLine().trim();
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine().trim().toLowerCase();

        if (!servico.especialidadeAceita(esp)) {
            System.out.println("Especialidade invalida! Use: clinica geral, fisioterapia, psicologia ou nutricao.");
            return;
        }

        boolean cadastrado;

        try {
            System.out.print("Tipo (1-Minimo / 2-Com registro e valor / 3-Completo): ");
            int tipo = Integer.parseInt(sc.nextLine());

            if (tipo == 1) {
                cadastrado = servico.cadastrarProfissional(nome, cpf, esp);
            } else if (tipo == 2) {
                System.out.print("Registro: ");
                String reg = sc.nextLine();
                System.out.print("Valor consulta: ");
                double valor = Double.parseDouble(sc.nextLine());
                String dadoEspecifico = lerDadoEspecificoProfissional(esp);
                cadastrado = servico.cadastrarProfissional(nome, cpf, esp, reg, valor, dadoEspecifico);
            } else if (tipo == 3) {
                System.out.print("Registro: ");
                String reg = sc.nextLine();
                System.out.print("Valor consulta: ");
                double valor = Double.parseDouble(sc.nextLine());
                String dadoEspecifico = lerDadoEspecificoProfissional(esp);
                ArrayList<HorarioDisponivel> horarios = lerHorariosProfissional();
                cadastrado = servico.cadastrarProfissional(nome, cpf, esp, reg, valor, horarios, dadoEspecifico);
            } else {
                System.out.println("Tipo invalido.");
                return;
            }

            if (cadastrado) {
                profissionais[totalProfissionais] = servico.buscarProfissionalPorNome(nome);
                totalProfissionais++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Informe um numero valido.");
            return;
        } catch (OperacaoInvalidaException | ProfissionalNaoEncontradoException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!cadastrado) {
            System.out.println("Nao foi possivel cadastrar profissional.");
            return;
        }
        System.out.println("Profissional cadastrado!");
    }

    public static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();

        System.out.print("Vai informar horários disponíveis? (1-Nao / 2-Sim): ");
        int tipo;
        try {
            tipo = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Informe um numero valido.");
            return;
        }

        System.out.print("Registro: ");
        String reg = sc.nextLine();
        System.out.print("Valor consulta: ");
        double valor;
        boolean atualizado;

        try {
            valor = Double.parseDouble(sc.nextLine());
            if (tipo == 1) {
                atualizado = servico.atualizarProfissional(nome, reg, valor);
            } else if (tipo == 2) {
                ArrayList<HorarioDisponivel> horarios = lerHorariosProfissional();
                atualizado = servico.atualizarProfissional(nome, reg, valor, horarios);
            } else {
                System.out.println("Tipo invalido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Informe um numero valido.");
            return;
        } catch (ProfissionalNaoEncontradoException | OperacaoInvalidaException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!atualizado) {
            System.out.println("Profissional nao encontrado.");
            return;
        }
        System.out.println("Profissional atualizado!");
    }

    public static String lerDadoEspecificoProfissional(String especialidade) {
        if (especialidade.equals("fisioterapia")) {
            System.out.print("Total de sessoes previstas: ");
            return sc.nextLine();
        }
        if (especialidade.equals("psicologia")) {
            System.out.print("Abordagem terapeutica: ");
            return sc.nextLine();
        }
        if (especialidade.equals("nutricao")) {
            System.out.print("Plano alimentar: ");
            return sc.nextLine();
        }

        System.out.print("Encaminhamento: ");
        return sc.nextLine();
    }

    public static ArrayList<HorarioDisponivel> lerHorariosProfissional() {
        ArrayList<HorarioDisponivel> horarios = new ArrayList<HorarioDisponivel>();

        System.out.println("Informe os horários disponíveis do profissional.");
        System.out.println("Use data disponível no formato DD/MM/AAAA.");
        System.out.println("Use turnos como manha, tarde ou noite.");
        System.out.print("Quantos horarios atende? ");
        int quantidade = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Data disponível do horario " + (i + 1) + " (DD/MM/AAAA): ");
            String dia = sc.nextLine();
            if (!validarFormatoData(dia)) {
                System.out.println("Formato de data inválido. Use DD/MM/AAAA.");
                i--;
                continue;
            }
            System.out.print("Turno do horario " + (i + 1) + " (manha/tarde/noite): ");
            String turno = sc.nextLine();
            horarios.add(new HorarioDisponivel(dia, turno));
        }

        return horarios;
    }

    public static void listarProfissionais() {
        ArrayList<Profissional> listaProfissionais = servico.listarProfissionais();
        if (listaProfissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional profissional : listaProfissionais) {
            System.out.println(profissional.exibirResumo());
        }
    }

    public static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine().trim().toLowerCase();
        ArrayList<Profissional> listaProfissionais = servico.filtrarProfissionaisPorEspecialidade(esp);

        if (listaProfissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado com essa especialidade.");
            return;
        }
        System.out.println("Profissionais encontrados:");
        for (Profissional profissional : listaProfissionais) {
            System.out.println(profissional.exibirResumo());
        }
    }

    public static int buscarIndiceProfissional(String nome) {
        if (nome == null) {
            return -1;
        }
        String nomeTratado = nome.trim().toLowerCase();
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getNome().trim().toLowerCase().equals(nomeTratado)) return i;
        }
        return -1;
    }

    // ---- CONSULTAS ----

    public static void menuConsultas() {
        Integer op = -1;
        while (op.intValue() != 0) {
            System.out.println("\n--- MENU DE CONSULTAS ---");
            System.out.println("1 - Agendar consulta escolhendo profissional");
            System.out.println("2 - Agendar consulta por especialidade");
            System.out.println("3 - Cancelar consulta");
            System.out.println("4 - Remarcar consulta");
            System.out.println("5 - Listar todas as consultas");
            System.out.println("6 - Buscar consultas por CPF do paciente");
            System.out.println("0 - Voltar ao menu principal");
            op = lerInteiro("Escolha uma opcao: ");
            if (op == null) {
                op = -1;
                continue;
            }

            switch (op.intValue()) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

// Coleta os dados informados pelo usuário e delega as validações e o agendamento ao ClinicaServico.
public static void agendarComProfissional() {
    System.out.println("--- Agendar consulta com profissional especifico ---");
    System.out.print("CPF do paciente: ");
    String cpf = sc.nextLine();

    System.out.print("Nome do profissional (como cadastrado): ");
    String nome = sc.nextLine();

    int idxProf = buscarIndiceProfissional(nome);
    if (idxProf == -1) {
        System.out.println("Profissional não encontrado. Verifique o nome cadastrado.");
        System.out.println("Operação de agendamento finalizada.");
        return;
    }

    if (profissionais[idxProf].getHorariosDisponiveis().isEmpty()) {
        System.out.println("Este profissional ainda não possui horários disponíveis. Atualize o cadastro do profissional antes de agendar.");
        System.out.println("Operação de agendamento finalizada.");
        return;
    }

    System.out.println("Horários disponíveis:");
    for (HorarioDisponivel horarioDisp : profissionais[idxProf].getHorariosDisponiveis()) {
        System.out.println("- " + horarioDisp.exibirResumo());
    }
    System.out.println("A disponibilidade é validada por data e turno.");

    System.out.print("Data da consulta (DD/MM/AAAA): ");
    String data = sc.nextLine();
    if (!validarFormatoData(data)) {
        System.out.println("Formato de data inválido. Use DD/MM/AAAA.");
        System.out.println("Operação de agendamento finalizada.");
        return;
    }

    System.out.print("Turno desejado (manha, tarde, noite): ");
    String turno = sc.nextLine().trim().toLowerCase();

    System.out.print("Horario da consulta (HH:MM, ex. 09:00): ");
    String horario = sc.nextLine();

    System.out.print("Tipo de consulta (pressione Enter para vazio): ");
    String tipo = sc.nextLine();

    try {
        servico.agendarConsultaPorProfissional(consultas, profissionais, totalProfissionais,
                cpf, nome, data, horario, tipo, data, turno);
        System.out.println("Consulta agendada com sucesso!");
    } catch (ConsultaNaoEncontradaException | PacienteNaoEncontradoException | PacienteInativoException |
            ProfissionalNaoEncontradoException | HorarioIndisponivelException |
            OperacaoInvalidaException e) {
        System.out.println(e.getMessage());
    } finally {
        System.out.println("Operação de agendamento finalizada.");
    }
}

// Realiza o agendamento buscando automaticamente um profissional da especialidade informada.
public static void agendarPorEspecialidade() {
    System.out.println("--- Agendar consulta por especialidade ---");
    System.out.print("CPF do paciente: ");
    String cpf = sc.nextLine();
    System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
    String especialidade = sc.nextLine().trim().toLowerCase();
    System.out.print("Data da consulta (DD/MM/AAAA): ");
    String data = sc.nextLine();
    if (!validarFormatoData(data)) {
        System.out.println("Formato de data inválido. Use DD/MM/AAAA.");
        System.out.println("Operação de agendamento por especialidade finalizada.");
        return;
    }
    System.out.print("Turno desejado (manha, tarde, noite): ");
    String turno = sc.nextLine().trim().toLowerCase();
    System.out.println("A disponibilidade é validada por data e turno.");
    System.out.print("Horario da consulta (HH:MM, ex. 14:00): ");
    String horario = sc.nextLine();
    System.out.print("Tipo de consulta (pressione Enter para vazio): ");
    String tipo = sc.nextLine();

    try {
        servico.agendarConsultaPorEspecialidade(
                consultas,
                profissionais,
                totalProfissionais,
                cpf,
                especialidade,
                data,
                horario,
                tipo,
                data,
                turno
        );

        System.out.println("Consulta agendada por especialidade com sucesso!");
    } catch (ConsultaNaoEncontradaException | PacienteNaoEncontradoException | PacienteInativoException |
            HorarioIndisponivelException e) {
        System.out.println(e.getMessage());
    } finally {
        System.out.println("Operação de agendamento por especialidade finalizada.");
    }
}

// Recebe os dados da consulta e solicita ao serviço o cancelamento da consulta.
public static void cancelarConsulta() {
    System.out.print("CPF do paciente: ");
    String cpf = sc.nextLine();
    System.out.print("Data da consulta (DD/MM/AAAA): ");
    String data = sc.nextLine();
    System.out.print("Horario da consulta (HH:MM): ");
    String horario = sc.nextLine();
    System.out.print("Informar motivo do cancelamento? (1-Nao / 2-Sim): ");
    int temMotivo = Integer.parseInt(sc.nextLine());
    String motivo = "";
    if (temMotivo == 2) {
        System.out.print("Motivo do cancelamento: ");
    }

    try {
        servico.cancelarConsulta(consultas, cpf, data, horario, motivo);
        System.out.println("Consulta cancelada com sucesso.");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        System.out.println("Operação de cancelamento finalizada.");
    }
}

// Coleta os novos dados da consulta e delega ao serviço a validação da remarcação.
public static void remarcarConsulta() {
    System.out.print("CPF do paciente: ");
    String cpf = sc.nextLine();
    System.out.print("Data original da consulta (DD/MM/AAAA): ");
    String dataOrig = sc.nextLine();
    System.out.print("Horario original da consulta (HH:MM): ");
    String horarioOrig = sc.nextLine();
    System.out.print("Nova data da consulta (DD/MM/AAAA): ");
    String novaData = sc.nextLine();
    if (!validarFormatoData(novaData)) {
        System.out.println("Formato de data inválido. Use DD/MM/AAAA.");
        System.out.println("Operação de remarcação finalizada.");
        return;
    }
    System.out.print("Novo turno da consulta (manha, tarde, noite): ");
    String novoTurno = sc.nextLine().trim().toLowerCase();
    System.out.print("Novo horario da consulta (HH:MM): ");
    String novoHorario = sc.nextLine();

    try {
        servico.remarcarConsulta(consultas, profissionais, totalProfissionais,
                cpf, dataOrig, horarioOrig, novaData, novoHorario, novoTurno);
        System.out.println("Consulta remarcada com sucesso!");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        System.out.println("Operação de remarcação finalizada.");
    }
}

public static void listarConsultas() {
    if (consultas.size() == 0) {
        System.out.println("Nenhuma consulta.");
        return;
    }

    for (int i = 0; i < consultas.size(); i++) {
        System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());   
    }
}

public static void buscarConsultasPorPaciente() {
    System.out.print("CPF: ");
    String cpf = sc.nextLine();

    boolean achou = false;

    for (int i = 0; i < consultas.size(); i++) {
        if (consultas.get(i).cpfPaciente.equals(cpf)) {
            System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
            achou = true;
        }
    }

    if (!achou) {
        System.out.println("Nenhuma consulta encontrada.");
    }
}

// verifica se ja tem consulta nesse horario com esse profissional
public static boolean temConflito(String nomeProf, String data, String horario) {
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

    // sugere proximo horario livre (de hora em hora, 08h ate 18h)
    public static String sugerirHorario(String nomeProf, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(nomeProf, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    // ---- ATENDIMENTOS ----

    public static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- MENU DE ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento de consulta");
            System.out.println("0 - Voltar ao menu principal");
            op = lerInteiro("Escolha uma opcao: ");

            if (op == 1) registrarAtendimento();
        }
    }

    public static void registrarAtendimento() {
        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }
        if (!consultas.get(idxConsulta).status.equals("agendada")) {
            System.out.println("So pode registrar atendimento em consulta agendada.");
            return;
        }

        System.out.print("Observacoes: ");
        String obs = sc.nextLine();

        System.out.print("Tipo de registro (1-So obs / 2-Com diagnostico / 3-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            atendimentos[totalAtendimentos] = new Atendimento(idxConsulta, obs);

        } else if (tipo == 2) {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();
            atendimentos[totalAtendimentos] = new Atendimento(idxConsulta, obs, diag);

        } else {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();

            System.out.print("Como informar procedimentos? (1-Um por vez / 2-Todos de uma vez): ");
            int forma = Integer.parseInt(sc.nextLine());

            String[] procs = new String[10];
            int qtdProcs = 0;

            if (forma == 1) {
                String proc = "";
                while (!proc.equals("fim") && qtdProcs < 10) {
                    System.out.print("Procedimento (ou 'fim'): ");
                    proc = sc.nextLine();
                    if (!proc.equals("fim")) {
                        procs[qtdProcs] = proc;
                        qtdProcs++;
                    }
                }
            } else {
                System.out.print("Quantos? ");
                qtdProcs = Integer.parseInt(sc.nextLine());
                if (qtdProcs > 10) qtdProcs = 10;
                for (int i = 0; i < qtdProcs; i++) {
                    System.out.print("Proc " + (i+1) + ": ");
                    procs[i] = sc.nextLine();
                }
            }
            atendimentos[totalAtendimentos] = new Atendimento(idxConsulta, obs, diag, procs, qtdProcs);
        }

        consultas.get(idxConsulta).realizar();
        totalAtendimentos++;
        System.out.println("\n--- RESUMO ---");
        System.out.println(atendimentos[totalAtendimentos - 1].exibirResumo());
        System.out.println("Consulta marcada como realizada.");
    }

    // ---- PAGAMENTOS ----

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- MENU DE PAGAMENTOS ---");
            System.out.println("1 - Registrar pagamento direto");
            System.out.println("2 - Registrar pagamento automatico");
            System.out.println("3 - Listar pagamentos registrados");
            System.out.println("0 - Voltar ao menu principal");
            op = lerInteiro("Escolha uma opcao: ");

            switch (op) {
                case 1: pagamentoDireto(); break;
                case 2: pagamentoAutomatico(); break;
                case 3: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void pagamentoDireto() {
        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 6): ");
            int parc = Integer.parseInt(sc.nextLine());
            try {
                pagamentos[totalPagamentos] = new PagamentoCartao(idxConsulta, valor, tipoPag, parc);
                if (parc > 1) {
                    double vlrFinalCartao = pagamentos[totalPagamentos].getValorFinal();
                    double vlrParc = Math.round((vlrFinalCartao / parc) * 100.0) / 100.0;
                    System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
                }
                totalPagamentos++;
                System.out.println("Pagamento registrado!");
            } catch (PagamentoInvalidoException e) {
                System.out.println("Erro no pagamento: " + e.getMessage());
            }
        } else if (tipoPag.equals("convenio")) {
            System.out.print("Nome do convenio: ");
            String nomeConvenio = sc.nextLine();
            Convenio convenio = buscarConvenioPorNome(nomeConvenio);

            String nomeProfConsulta = consultas.get(idxConsulta).nomeProfissional;
            int idxProfConsulta = buscarIndiceProfissional(nomeProfConsulta);
            String especialidade = profissionais[idxProfConsulta].getEspecialidade();

            try {
                pagamentos[totalPagamentos] = new PagamentoConvenio(idxConsulta, valor, tipoPag, convenio, especialidade);
                totalPagamentos++;
                System.out.println("Pagamento registrado!");
            } catch (ConvenioNaoCobreException e) {
                System.out.println("Erro no pagamento: " + e.getMessage());
            }
        } else {
            pagamentos[totalPagamentos] = new PagamentoDinheiro(idxConsulta, valor, tipoPag);
            totalPagamentos++;
            System.out.println("Pagamento registrado!");
        }
    }

    public static void pagamentoAutomatico() {
        System.out.print("Indice da consulta: ");
        int idxConsulta = Integer.parseInt(sc.nextLine());

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        // obtem valor do profissional
        String nomeProf = consultas.get(idxConsulta).nomeProfissional;
        int idxProf = buscarIndiceProfissional(nomeProf);
        double valorBase = profissionais[idxProf].getValorConsulta();

        // verifica convenio e tipo
        String cpfPac = consultas.get(idxConsulta).cpfPaciente;
        boolean temConvenio;
        try {
            temConvenio = servico.pacienteTemConvenio(cpfPac);
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }
        boolean ehRetorno = consultas.get(idxConsulta).tipo.equals("retorno");

        double desconto = 0;
        if (ehRetorno) desconto = desconto + 20;
        if (temConvenio) desconto = desconto + 40;

        System.out.print("Tem multa pendente? (1-Nao / 2-Sim): ");
        int temMulta = Integer.parseInt(sc.nextLine());
        double valorMulta = 0;

        double valorFinal;
        double valorDescontado = valorBase - (valorBase * desconto / 100);
        if (temMulta == 1) {
            valorFinal = valorDescontado;
        } else {
            System.out.print("Valor da multa: ");
            valorMulta = Double.parseDouble(sc.nextLine());
            valorFinal = valorDescontado + valorMulta;
        }
        if (valorFinal < 0) valorFinal = 0;

        System.out.println("Valor base: R$" + valorBase);
        System.out.println("Desconto: " + desconto + "%");
        if (valorMulta > 0) System.out.println("Multa: R$" + valorMulta);
        double vlrFinalArredondado = Math.round(valorFinal * 100.0) / 100.0;
        System.out.println("Valor final: R$" + vlrFinalArredondado);

        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 6): ");
            int parc = Integer.parseInt(sc.nextLine());
            try {
                pagamentos[totalPagamentos] = new PagamentoCartao(idxConsulta, valorFinal, tipoPag, parc, true);
                double vlrParc = Math.round((valorFinal / parc) * 100.0) / 100.0;
                System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
                totalPagamentos++;
                System.out.println("Pagamento registrado!");
            } catch (PagamentoInvalidoException e) {
                System.out.println("Erro no pagamento: " + e.getMessage());
            }
        } else if (tipoPag.equals("convenio")) {
            Convenio convenio = null;
        try {
            convenio = servico.buscarPacientePorCpf(cpfPac).getConvenio();
        }   catch (PacienteNaoEncontradoException e) {
                System.out.println(e.getMessage());
            return;
        }
            String especialidade = profissionais[idxProf].getEspecialidade();

            try {
                pagamentos[totalPagamentos] = new PagamentoConvenio(idxConsulta, valorBase, tipoPag, convenio, especialidade);
                totalPagamentos++;
                System.out.println("Pagamento registrado!");
            } catch (ConvenioNaoCobreException e) {
                System.out.println("Erro no pagamento: " + e.getMessage());
            }
        } else {
            pagamentos[totalPagamentos] = new PagamentoDinheiro(idxConsulta, valorFinal, tipoPag, true);
            totalPagamentos++;
            System.out.println("Pagamento registrado!");
        }
    }

    public static void listarPagamentos() {
        if (totalPagamentos == 0) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (int i = 0; i < totalPagamentos; i++) {
            System.out.println(pagamentos[i].exibirResumo());
        }
    }

    public static Consulta[] consultasComoArray() {
        return consultas.toArray(new Consulta[consultas.size()]);
    }

    // ---- RELATORIOS ----

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- MENU DE RELATORIOS ---");
            System.out.println("1 - Gerar relatorio geral");
            System.out.println("2 - Gerar relatorio por profissional");
            System.out.println("3 - Gerar relatorio por periodo");
            System.out.println("4 - Gerar resumo financeiro");
            System.out.println("0 - Voltar ao menu principal");
            op = lerInteiro("Escolha uma opcao: ");

            switch (op) {
                case 1:
                    Relatorio.gerarRelatorio(consultasComoArray(), consultas.size(), atendimentos, totalAtendimentos);
                    break;
                case 2:
                    System.out.print("Nome do profissional: ");
                    String nome = sc.nextLine();
                    Relatorio.gerarRelatorio(consultasComoArray(), consultas.size(), atendimentos, totalAtendimentos, nome);
                    break;
                case 3:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    Relatorio.gerarRelatorio(consultasComoArray(), consultas.size(), atendimentos, totalAtendimentos, ini, fim);
                    break;
                case 4:
                    Relatorio.gerarResumoFinanceiro(consultasComoArray(), consultas.size(), pagamentos, totalPagamentos, multas, totalMultas);
                    break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }
}
