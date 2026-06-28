/**
 * Interface que define um contrato comum para o ciclo de vida das consultas,
 * garantindo que todas as classes implementem as operações de agendar,
 * cancelar e remarcar.
 */
public interface Agendavel {
    void agendar();
    void cancelar();
    void remarcar();
}
