public class Paciente extends Pessoa {
    private int idade;
    private String convenioNome;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone, "");
        this.idade = idade;
        this.convenioNome = "";
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone, "");
        this.idade = idade;
        this.convenioNome = convenioNome;
        this.ativo = true;
    }

    public void complementar(int idade, String telefone) {
        this.idade = idade;
        setTelefone(telefone);
    }

    public void complementar(int idade, String telefone, String convenioNome) {
        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = convenioNome;
    }

    public void desativar() {
        this.ativo = false;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getConvenioNome() {
        return convenioNome;
    }

    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
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
