package med.app.api.endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
}
