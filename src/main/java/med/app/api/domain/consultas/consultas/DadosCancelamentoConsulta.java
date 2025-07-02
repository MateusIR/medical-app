package med.app.api.domain.consultas.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta
) {
}