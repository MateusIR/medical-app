package med.app.api.medico;

import jakarta.validation.constraints.NotNull;
import med.app.api.endereco.DadosEndereco;

public record DadosAtualizarMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {
}
