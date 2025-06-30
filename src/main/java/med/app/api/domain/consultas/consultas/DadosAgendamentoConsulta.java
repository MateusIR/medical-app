package med.app.api.domain.consultas.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.app.api.domain.medico.Especialidade;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosAgendamentoConsulta  (
                                         @NotNull
                                         Long idMedico,
                                         @NotNull
                                         Long idPaciente,

                                         Especialidade especialidade,

                                         @NotNull
                                         @Future
                                         LocalDateTime data) {
}
