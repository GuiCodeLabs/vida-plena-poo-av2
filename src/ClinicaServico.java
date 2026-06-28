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
        if (profissionaisPorNome.containsKey(profissional.nome)) {
            return false;
        }

        profissionais.add(profissional);
        profissionaisPorNome.put(profissional.nome, profissional);
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
        return profissionaisPorNome.get(nome);
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
}
