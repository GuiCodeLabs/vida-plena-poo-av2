/**
 * Classe Psicologo, subclasse de Profissional.
 * Deve conter atributos específicos como abordagem terapêutica.
 */
public class Psicologo extends Profissional {
    public Psicologo() {
        super("", "psicologia");
    }
    public Psicologo(String nome) {
        super(nome, "psicologia");
    }
}
