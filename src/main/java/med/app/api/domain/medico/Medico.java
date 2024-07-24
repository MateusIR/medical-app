package med.app.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.app.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String crm;

    private String email;

    private String telefone;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastoMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.email = dados.email();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInfo(DadosAtualizarMedico dados) {

        if (dados.nome() != null){ this.nome = dados.nome(); }

        if (dados.telefone() != null){ this.telefone = dados.telefone(); }

        if (dados.endereco() != null){ this.endereco.atualizarInfo(dados.endereco()); }
    }

    public void desativar() {
        this.ativo = false;
    }
}
