/**
 * Exceção personalizada utilizada para indicar que o profissional
 * não possui disponibilidade no horário solicitado para a consulta.
 */
public class HorarioIndisponivelException extends Exception {
    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public HorarioIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
