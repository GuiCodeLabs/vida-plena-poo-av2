public class Paciente extends Pessoa {
    private int idade;
    private String convenioNome;
    private boolean ativo;

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

    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenioNome(convenioNome);
    }

    public void desativar() {
        setAtivo(false);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        }
    }

    public String getConvenioNome() {
        return convenioNome;
    }

    public void setConvenioNome(String convenioNome) {
        if (convenioNome == null) {
            this.convenioNome = "";
        } else {
            this.convenioNome = convenioNome.trim();
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!isAtivo()) {
            status = "Nao";
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + getIdade()
                + " | Tel: " + getTelefone() + " | Convenio: " + getConvenioNome()
                + " | Ativo: " + status;
    }
}
