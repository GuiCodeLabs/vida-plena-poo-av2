public class Profissional extends Pessoa {

    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private String[] diasDisponiveis;
    private int totalDias;

    public Profissional(String nome, String especialidade) {
        super(nome);
        setEspecialidade(especialidade);
        setRegistroProfissional("");
        setValorConsulta(0);
        this.diasDisponiveis = new String[7];
        this.totalDias = 0;
    }

    public Profissional(String nome, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.diasDisponiveis = new String[7];
        this.totalDias = 0;
    }

    public Profissional(String nome, String especialidade, String registroProfissional,
                        double valorConsulta, String[] dias, int totalDias) {
        super(nome);
        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.diasDisponiveis = new String[7];
        this.totalDias = totalDias;
        for (int i = 0; i < totalDias; i++) {
            this.diasDisponiveis[i] = dias[i];
        }
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public String[] getDiasDisponiveis() {
        String[] dias = new String[totalDias];
        for (int i = 0; i < totalDias; i++) {
            dias[i] = diasDisponiveis[i];
        }
        return dias;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void atualizar(String registro, double valor) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
    }

    public void atualizar(String registro, double valor, String[] dias, int totalDias) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
        this.totalDias = totalDias;
        for (int i = 0; i < totalDias; i++) {
            this.diasDisponiveis[i] = dias[i];
        }
    }

    public boolean atendeNoDia(String dia) {
        for (int i = 0; i < totalDias; i++) {
            if (diasDisponiveis[i].equals(dia)) {
                return true;
            }
        }
        return false;
    }

    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    public String exibirResumo() {
        String dias = "";
        for (int i = 0; i < totalDias; i++) {
            if (i > 0) dias = dias + ", ";
            dias = dias + diasDisponiveis[i];
        }
        return "Nome: " + nome + " | Espec: " + especialidade + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta + " | Dias: " + dias;
    }
}
