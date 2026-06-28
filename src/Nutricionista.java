/**
 * Classe Nutricionista, subclasse de Profissional.
 * Deve conter atributos específicos como planoAlimentar.
 */
public class Nutricionista extends Profissional {
    public Nutricionista() {
        super("", "nutricao");
    }
    public Nutricionista(String nome) {
        super(nome, "nutricao");
    }
}
