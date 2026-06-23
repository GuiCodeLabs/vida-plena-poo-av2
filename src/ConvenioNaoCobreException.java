/**
 * Exceção personalizada para tratar falta de cobertura de especialidades do convênio do paciente.
 */
public class ConvenioNaoCobreException extends Exception {
    public ConvenioNaoCobreException(String mensagem) {
        super(mensagem);
    }

    public ConvenioNaoCobreException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
