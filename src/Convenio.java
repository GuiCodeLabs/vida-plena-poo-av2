import java.util.ArrayList;

// Convênio é uma entidade independente: pode existir no sistema mesmo sem estar
// associado a um paciente específico.
public class Convenio {
    private String nome;
    private double percentualCobertura;
    // ArrayList<String>: armazena as especialidades cobertas pelo convênio
    // e permite percorrer a lista ao verificar se uma especialidade é atendida.
    private ArrayList<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura) {
        this.nome = "";
        this.percentualCobertura = 0;
        this.especialidadesCobertas = new ArrayList<String>();
        setNome(nome);
        setPercentualCobertura(percentualCobertura);
    }

    public Convenio(String nome, double percentualCobertura, String[] especialidadesCobertas) {
        this(nome, percentualCobertura);
        setEspecialidadesCobertas(especialidadesCobertas);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().equals("")) {
            this.nome = nome.trim();
        }
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public void setPercentualCobertura(double percentualCobertura) {
        if (percentualCobertura < 0) {
            this.percentualCobertura = 0;
        } else if (percentualCobertura > 100) {
            this.percentualCobertura = 100;
        } else {
            this.percentualCobertura = percentualCobertura;
        }
    }

    public String[] getEspecialidadesCobertas() {
        return especialidadesCobertas.toArray(new String[especialidadesCobertas.size()]);
    }

    public void setEspecialidadesCobertas(String[] especialidadesCobertas) {
        this.especialidadesCobertas.clear();
        if (especialidadesCobertas == null) {
            return;
        }
        for (int i = 0; i < especialidadesCobertas.length; i++) {
            adicionarEspecialidadeCoberta(especialidadesCobertas[i]);
        }
    }

    public void adicionarEspecialidadeCoberta(String especialidade) {
        String especialidadeTratada = normalizarTexto(especialidade);
        if (!especialidadeTratada.equals("") && !this.especialidadesCobertas.contains(especialidadeTratada)) {
            this.especialidadesCobertas.add(especialidadeTratada);
        }
    }

    public boolean cobreEspecialidade(String especialidade) {
        String especialidadeTratada = normalizarTexto(especialidade);
        return this.especialidadesCobertas.contains(especialidadeTratada);
    }

    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.trim().toLowerCase();
    }
}
