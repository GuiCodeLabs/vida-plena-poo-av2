/**
 * Exceção personalizada utilizada quando uma consulta
 * não é encontrada durante uma operação do sistema.
 */
public class ConsultaNaoEncontradaException extends Exception {
    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ConsultaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
