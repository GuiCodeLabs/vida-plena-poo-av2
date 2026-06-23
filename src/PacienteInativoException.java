/**
 * Exceção personalizada para tratar tentativas de agendamento em paciente inativo.
 */
public class PacienteInativoException extends Exception {
    public PacienteInativoException(String mensagem) {
        super(mensagem);
    }

    public PacienteInativoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
