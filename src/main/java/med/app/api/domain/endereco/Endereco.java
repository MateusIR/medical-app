package med.app.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco endereco) {
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }

    public void atualizarInfo(DadosEndereco endereco) {
        if (endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if (endereco.complemento() != null){
            this.complemento = endereco.complemento();
        }

    }
}
