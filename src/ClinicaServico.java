import java.util.ArrayList;

/**
 * Classe responsável por centralizar as regras de negócio
 * relacionadas ao gerenciamento de consultas da clínica.
 */
public class ClinicaServico {

    // Localiza um paciente pelo CPF para validar operações envolvendo consultas.
    public static int buscarIndicePaciente(Paciente[] pacientes, int totalPacientes, String cpf) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].cpf.equals(cpf)) {
                return i;
            }
        }
        return -1;
    }

    // Localiza um profissional pelo nome para validar o agendamento.
    public static int buscarIndiceProfissional(Profissional[] profissionais, int totalProfissionais, String nome) {
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].nome.equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    // Verifica se já existe uma consulta agendada para o mesmo profissional, data e horário.
    public static boolean temConflito(ArrayList<Consulta> consultas, String nomeProf, String data, String horario) {
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

    // Procura um horário livre para sugerir ao paciente quando o horário informado estiver indisponível.
    public static String sugerirHorario(ArrayList<Consulta> consultas, String nomeProf, String data) {
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

    // Centraliza todas as validações necessárias antes de realizar um novo agendamento com um profissional.
    public static void agendarConsultaPorProfissional(
            ArrayList<Consulta> consultas,
            Paciente[] pacientes,
            int totalPacientes,
            Profissional[] profissionais,
            int totalProfissionais,
            String cpf,
            String nomeProf,
            String data,
            String horario,
            String tipo,
            String diaSemana
    ) throws ConsultaNaoEncontradaException, PacienteInativoException,
            ProfissionalNaoEncontradoException, HorarioIndisponivelException,
            OperacaoInvalidaException {

        int idxPac = buscarIndicePaciente(pacientes, totalPacientes, cpf);

        if (idxPac == -1) {
            throw new ConsultaNaoEncontradaException("Paciente não encontrado para agendamento.");
        }

        if (!pacientes[idxPac].ativo) {
            throw new PacienteInativoException("Paciente inativo. Não é possível agendar consulta.");
        }

        int idxProf = buscarIndiceProfissional(profissionais, totalProfissionais, nomeProf);

        if (idxProf == -1) {
            throw new ProfissionalNaoEncontradoException("Profissional não encontrado.");
        }

        if (profissionais[idxProf].valorConsulta == 0) {
            throw new OperacaoInvalidaException("Profissional sem valor definido. Não pode agendar.");
        }

        if (!profissionais[idxProf].atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional não atende nesse dia.");
        }

        if (temConflito(consultas, nomeProf, data, horario)) {
            throw new HorarioIndisponivelException("Horário ocupado para esse profissional.");
        }

        if (tipo.equals("")) {
            consultas.add(new Consulta(cpf, nomeProf, data, horario));
        } else {
            consultas.add(new Consulta(cpf, nomeProf, data, horario, tipo));
        }
    }

    // Busca um profissional disponível da especialidade informada e realiza o agendamento.
    public static void agendarConsultaPorEspecialidade(
            ArrayList<Consulta> consultas,
            Paciente[] pacientes,
            int totalPacientes,
            Profissional[] profissionais,
            int totalProfissionais,
            String cpf,
            String especialidade,
            String data,
            String horario,
            String diaSemana
    ) throws ConsultaNaoEncontradaException, PacienteInativoException,
            HorarioIndisponivelException {

        int idxPac = buscarIndicePaciente(pacientes, totalPacientes, cpf);

        if (idxPac == -1) {
            throw new ConsultaNaoEncontradaException("Paciente não encontrado para agendamento.");
        }

        if (!pacientes[idxPac].ativo) {
            throw new PacienteInativoException("Paciente inativo. Não é possível agendar consulta.");
        }

        int idxProf = -1;

        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].especialidade.equals(especialidade)
                    && profissionais[i].valorConsulta > 0
                    && profissionais[i].atendeNoDia(diaSemana)
                    && !temConflito(consultas, profissionais[i].nome, data, horario)) {
                idxProf = i;
                break;
            }
        }

        if (idxProf == -1) {
            throw new HorarioIndisponivelException("Nenhum profissional disponível para essa especialidade.");
        }

        consultas.add(new Consulta(cpf, profissionais[idxProf].nome, data, horario));
    }

    // Localiza uma consulta utilizando CPF, data e horário.
    public static Consulta buscarConsulta(
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

    // Executa as validações antes de cancelar uma consulta.
    public static void cancelarConsulta(
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

        if (motivo.equals("")) {
            consulta.cancelar();
        } else {
            consulta.cancelar(motivo);
        }
    }

    // Valida a disponibilidade do novo horário antes de realizar a remarcação da consulta.
    public static void remarcarConsulta(
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