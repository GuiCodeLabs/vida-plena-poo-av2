/**
 * Exceção personalizada utilizada quando uma operação
 * não pode ser realizada devido às regras de negócio do sistema.
 */
public class OperacaoInvalidaException extends Exception {
    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    public OperacaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
