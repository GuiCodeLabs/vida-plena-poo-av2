import java.util.ArrayList;

public abstract class Profissional extends Pessoa {
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private ArrayList<HorarioDisponivel> horariosDisponiveis;

    public Profissional(String nome, String especialidade) {
        super(nome, "");
        setEspecialidade(especialidade);
        setRegistroProfissional("");
        setValorConsulta(0);
        horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional("");
        setValorConsulta(0);
        horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, "");
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String especialidade, String registroProfissional,
                        double valorConsulta, ArrayList<HorarioDisponivel> horarios) {
        super(nome, "");
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        setHorariosDisponiveis(horarios);
    }

    public Profissional(String nome, String cpf, String especialidade, String registroProfissional,
                        double valorConsulta, ArrayList<HorarioDisponivel> horarios) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        setHorariosDisponiveis(horarios);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade nao pode ser vazia.");
        }
        String especialidadeTratada = especialidade.trim().toLowerCase();
        if (!especialidadeValida(especialidadeTratada)) {
            throw new IllegalArgumentException("Especialidade invalida.");
        }
        this.especialidade = especialidadeTratada;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        if (registroProfissional == null) {
            registroProfissional = "";
        }
        this.registroProfissional = registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta < 0) {
            throw new IllegalArgumentException("Valor da consulta nao pode ser negativo.");
        }
        this.valorConsulta = valorConsulta;
    }

    public ArrayList<HorarioDisponivel> getHorariosDisponiveis() {
        return new ArrayList<>(horariosDisponiveis);
    }

    public void setHorariosDisponiveis(ArrayList<HorarioDisponivel> horarios) {
        horariosDisponiveis = new ArrayList<>();
        if (horarios == null) {
            return;
        }

        for (HorarioDisponivel horario : horarios) {
            if (horario != null) {
                horariosDisponiveis.add(horario);
            }
        }
    }

    public void atualizar(String registro, double valor) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
    }

    public void atualizar(String registro, double valor, ArrayList<HorarioDisponivel> horarios) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
        setHorariosDisponiveis(horarios);
    }

    public boolean atendeNoDia(String dia) {
        for (HorarioDisponivel horario : horariosDisponiveis) {
            if (horario.atendeNoDia(dia)) {
                return true;
            }
        }
        return false;
    }

    public boolean atendeNoHorario(String dia, String turno) {
        for (HorarioDisponivel horario : horariosDisponiveis) {
            if (horario.atendeNoHorario(dia, turno)) {
                return true;
            }
        }
        return false;
    }

    public static boolean especialidadeValida(String esp) {
        if (esp == null) return false;
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    public String exibirResumo() {
        String horarios = "";
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            if (i > 0) horarios = horarios + ", ";
            horarios = horarios + horariosDisponiveis.get(i).exibirResumo();
        }
        String cpf = getCpf().equals("") ? "-" : getCpf();

        return "Nome: " + getNome() + " | CPF: " + cpf + " | Espec: " + especialidade
                + " | Reg: " + registroProfissional + " | Valor: R$" + valorConsulta
                + " | Horarios: " + (horarios.isEmpty() ? "nao definido" : horarios);
    }
}
