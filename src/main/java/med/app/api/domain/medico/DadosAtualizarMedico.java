package med.app.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.app.api.domain.endereco.DadosEndereco;

public record DadosAtualizarMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {
}
