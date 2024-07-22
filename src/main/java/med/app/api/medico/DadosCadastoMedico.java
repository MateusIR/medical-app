package med.app.api.medico;

import med.app.api.endereco.DadosEndereco;

public record DadosCadastoMedico(String nome,
                                 String email,
                                 String crm,
                                 Especialidade especialidade,
                                 DadosEndereco endereco) {
}
