// Exception de negocio (jornada 30): usada quando o convenio do paciente
// nao cobre a especialidade da consulta sendo paga.
public class ConvenioNaoCobreException extends Exception {
    public ConvenioNaoCobreException(String mensagem) {
        super(mensagem);
    }
}