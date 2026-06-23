/**
 * Exceção personalizada para tratar operações inconsistentes no fluxo do sistema.
 */
public class OperacaoInvalidaException extends Exception {
    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    public OperacaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
