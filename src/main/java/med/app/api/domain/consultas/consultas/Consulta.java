package med.app.api.domain.consultas.consultas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.app.api.domain.medico.Medico;
import med.app.api.domain.paciente.Paciente;
import javax.net.ssl.SSLSession;
import java.time.LocalDateTime;

@Table(name ="consultas")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    private LocalDateTime data; 
    private Boolean cancelada;


    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.cancelada = false;
    }

    public void cancelar() {
        this.cancelada = true;
    }
}
