public class ClinicaServico {
    private Profissional[] profissionais;
    private int totalProfissionais;

    public ClinicaServico() {
        profissionais = new Profissional[50];
        totalProfissionais = 0;
    }

    public boolean especialidadeAceita(String especialidade) {
        return Profissional.especialidadeValida(especialidade);
    }

    public boolean cadastrarProfissional(String nome, String especialidade) {
        return cadastrarProfissional(new Profissional(nome, especialidade));
    }

    public boolean cadastrarProfissional(String nome, String especialidade,
                                         String registroProfissional, double valorConsulta) {
        return cadastrarProfissional(
                new Profissional(nome, especialidade, registroProfissional, valorConsulta)
        );
    }

    public boolean cadastrarProfissional(String nome, String especialidade,
                                         String registroProfissional, double valorConsulta,
                                         String[] dias, int totalDias) {
        return cadastrarProfissional(
                new Profissional(nome, especialidade, registroProfissional, valorConsulta, dias, totalDias)
        );
    }

    private boolean cadastrarProfissional(Profissional profissional) {
        if (profissional == null) {
            return false;
        }
        if (!especialidadeAceita(profissional.getEspecialidade())) {
            return false;
        }
        if (totalProfissionais >= profissionais.length) {
            return false;
        }

        profissionais[totalProfissionais] = profissional;
        totalProfissionais++;
        return true;
    }

    public boolean atualizarProfissional(String nome, String registro, double valor) {
        Profissional profissional = buscarProfissionalPorNome(nome);
        if (profissional == null) {
            return false;
        }

        profissional.atualizar(registro, valor);
        return true;
    }

    public boolean atualizarProfissional(String nome, String registro, double valor,
                                         String[] dias, int totalDias) {
        Profissional profissional = buscarProfissionalPorNome(nome);
        if (profissional == null) {
            return false;
        }

        profissional.atualizar(registro, valor, dias, totalDias);
        return true;
    }

    public Profissional buscarProfissionalPorNome(String nome) {
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].nome.equals(nome)) {
                return profissionais[i];
            }
        }
        return null;
    }

    public Profissional[] listarProfissionais() {
        Profissional[] lista = new Profissional[totalProfissionais];
        for (int i = 0; i < totalProfissionais; i++) {
            lista[i] = profissionais[i];
        }
        return lista;
    }

    public Profissional[] filtrarProfissionaisPorEspecialidade(String especialidade) {
        int quantidade = 0;
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getEspecialidade().equals(especialidade)) {
                quantidade++;
            }
        }

        Profissional[] filtrados = new Profissional[quantidade];
        int posicao = 0;
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getEspecialidade().equals(especialidade)) {
                filtrados[posicao] = profissionais[i];
                posicao++;
            }
        }
        return filtrados;
    }
}
