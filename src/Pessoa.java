public abstract class Pessoa {
    private String nome = "";
    private String cpf = "";
    private String telefone = "";
    private String dataNascimento = "";

    public Pessoa(String nome, String cpf) {
        this(nome, cpf, "", "");
    }

    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().equals("")) {
            this.nome = nome.trim();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.trim().equals("")) {
            this.cpf = cpf.trim();
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null) {
            this.telefone = "";
        } else {
            this.telefone = telefone.trim();
        }
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        if (dataNascimento == null) {
            this.dataNascimento = "";
        } else {
            this.dataNascimento = dataNascimento.trim();
        }
    }

    public abstract String exibirResumo();
}
