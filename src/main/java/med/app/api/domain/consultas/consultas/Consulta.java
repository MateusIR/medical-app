package med.app.api.domain.consultas.consultas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.app.api.domain.medico.Medico;
import med.app.api.domain.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name ="consultas")
@Entity(name = "consulta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    private LocalDate data;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data) {
    }
}
