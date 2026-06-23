/**
 * Exceção personalizada para tratar indisponibilidade do profissional ou conflito de horário.
 */
public class HorarioIndisponivelException extends Exception {
    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public HorarioIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
