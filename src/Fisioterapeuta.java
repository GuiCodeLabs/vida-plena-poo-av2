/**
 * Classe Fisioterapeuta, subclasse de Profissional.
 * Deve conter atributos específicos como totalSessoesPrevistas.
 */
public class Fisioterapeuta extends Profissional {
    public Fisioterapeuta() {
        super("", "fisioterapia");
    }
    public Fisioterapeuta(String nome) {
        super(nome, "fisioterapia");
    }
}
