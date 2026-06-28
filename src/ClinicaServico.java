import java.util.ArrayList;
import java.util.HashMap;

public class ClinicaServico {
    private ArrayList<Profissional> profissionais;
    private HashMap<String, Profissional> profissionaisPorNome;

    public ClinicaServico() {
        profissionais = new ArrayList<>();
        profissionaisPorNome = new HashMap<>();
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
                criarProfissional(
                        nome,
                        especialidade,
                        registroProfissional,
                        valorConsulta,
                        new ArrayList<>(),
                        dadoEspecifico
                )
        );
    }

    public boolean cadastrarProfissional(String nome, String especialidade,
                                         String registroProfissional, double valorConsulta,
                                         ArrayList<HorarioDisponivel> horarios,
                                         String dadoEspecifico)
            throws OperacaoInvalidaException {
        return cadastrarProfissional(
                criarProfissional(
                        nome,
                        especialidade,
                        registroProfissional,
                        valorConsulta,
                        horarios,
                        dadoEspecifico
                )
        );
    }

    private Profissional criarProfissional(String nome, String especialidade)
            throws OperacaoInvalidaException {
        validarNome(nome);
        validarEspecialidade(especialidade);
        if (especialidade.equals("clinica geral")) return new ClinicoGeral(nome);
        if (especialidade.equals("fisioterapia")) return new Fisioterapeuta(nome);
        if (especialidade.equals("psicologia")) return new Psicologo(nome);
        if (especialidade.equals("nutricao")) return new Nutricionista(nome);
        return null;
    }

    private Profissional criarProfissional(String nome, String especialidade,
                                           String registroProfissional, double valorConsulta,
                                           ArrayList<HorarioDisponivel> horarios,
                                           String dadoEspecifico)
            throws OperacaoInvalidaException {
        validarNome(nome);
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
        return null;
    }

    private boolean cadastrarProfissional(Profissional profissional)
            throws OperacaoInvalidaException {
        if (profissional == null) {
            throw new OperacaoInvalidaException("Profissional invalido.");
        }
        if (!especialidadeAceita(profissional.getEspecialidade())) {
            throw new OperacaoInvalidaException("Especialidade invalida.");
        }
        if (profissionaisPorNome.containsKey(profissional.nome)) {
            throw new OperacaoInvalidaException("Ja existe profissional com esse nome.");
        }

        profissionais.add(profissional);
        profissionaisPorNome.put(profissional.nome, profissional);
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
        return new ArrayList<>(profissionais);
    }

    public ArrayList<Profissional> filtrarProfissionaisPorEspecialidade(String especialidade) {
        ArrayList<Profissional> filtrados = new ArrayList<>();
        for (Profissional profissional : profissionais) {
            if (profissional.getEspecialidade().equals(especialidade)) {
                filtrados.add(profissional);
            }
        }
        return filtrados;
    }

    private void validarNome(String nome) throws OperacaoInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
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
}
