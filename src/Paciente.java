public class Paciente extends Pessoa {
    private int idade;
    // ASSOCIAÇÃO: Paciente conhece Convênio, mas ambos existem independentemente.
    private Convenio convenio;
    private boolean ativo;

    // SOBRECARGA: construtores com o mesmo nome, mas parâmetros diferentes,
    // permitindo cadastro mínimo, cadastro com dados adicionais e cadastro completo.
    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        setIdade(0);
        setConvenioNome("");
        setAtivo(true);
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone, "");
        setIdade(idade);
        setConvenioNome("");
        setAtivo(true);
    }

    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone, "");
        setIdade(idade);
        setConvenioNome(convenioNome);
        setAtivo(true);
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone, "");
        setIdade(idade);
        setConvenio(convenio);
        setAtivo(true);
    }

    // SOBRECARGA: métodos complementar com parâmetros diferentes para atualizar
    // dados do paciente em níveis diferentes de detalhe.
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenioNome(convenioNome);
    }

    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenio(convenio);
    }

    public void desativar() {
        setAtivo(false);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 130) {
            this.idade = idade;
        }
    }

    public String getConvenioNome() {
        if (convenio == null) {
            return "";
        }
        return convenio.getNome();
    }

    public void setConvenioNome(String convenioNome) {
        if (convenioNome == null || convenioNome.trim().equals("")) {
            setConvenio(null);
        } else {
            setConvenio(new Convenio(convenioNome, 0));
        }
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public boolean temConvenio() {
        return convenio != null;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // SOBRESCRITA: Paciente redefine o método exibirResumo() herdado de Pessoa
    // para mostrar dados próprios, como idade, status ativo e convênio.
    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!isAtivo()) {
            status = "Nao";
        }
        String convenioResumo = "Sem convenio";
        if (temConvenio()) {
            convenioResumo = getConvenioNome();
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + getIdade()
                + " | Tel: " + getTelefone() + " | Convenio: " + convenioResumo
                + " | Ativo: " + status;
    }
}
