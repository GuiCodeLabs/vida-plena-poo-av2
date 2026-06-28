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
        String cpfTratado = extrairDigitos(cpf);
        if (!cpfTratado.equals("")) {
            this.cpf = cpfTratado;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = extrairDigitos(telefone);
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

    public abstract String exibirResumo();
}
