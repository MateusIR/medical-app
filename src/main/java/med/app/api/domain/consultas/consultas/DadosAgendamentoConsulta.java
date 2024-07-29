package med.app.api.domain.consultas.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosAgendamentoConsulta  (Long id,

                                         @NotNull
                                         Long idMedico,
                                         @NotNull
                                         Long idPaciente,

                                         @NotNull
                                         @Future
                                         LocalDateTime data) {
}
