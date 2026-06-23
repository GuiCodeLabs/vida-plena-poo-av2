/**
 * Exceção personalizada para tratar caso em que o paciente não é localizado.
 */
public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PacienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
