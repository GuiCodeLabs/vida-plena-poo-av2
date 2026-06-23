/**
 * Exceção personalizada para tratar profissional não localizado no sistema.
 */
public class ProfissionalNaoEncontradoException extends Exception {
    public ProfissionalNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProfissionalNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
