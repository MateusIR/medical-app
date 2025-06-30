package med.app.api.domain;

public class ValidacaoExeption extends RuntimeException {
    public ValidacaoExeption(String idDoPacienteNãoExiste) {
        super(idDoPacienteNãoExiste);
    }
}
