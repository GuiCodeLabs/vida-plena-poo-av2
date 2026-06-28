/**
 * Classe ClinicoGeral, subclasse de Profissional.
 * Deve conter atributos específicos como encaminhamento.
 */
public class ClinicoGeral extends Profissional {
    public ClinicoGeral() {
        super("", "clinica geral");
    }
    public ClinicoGeral(String nome) {
        super(nome, "clinica geral");
    }
}
