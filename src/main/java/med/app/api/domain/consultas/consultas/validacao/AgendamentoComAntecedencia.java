package med.app.api.domain.consultas.consultas.validacao;

import med.app.api.domain.ValidacaoExeption;
import med.app.api.domain.consultas.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class AgendamentoComAntecedencia implements ValidacoesConsulta{

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var dataConsulta = dadosAgendamentoConsulta.data();
        var horarioAtual = LocalDateTime.now();
        var diferencaHorasEmMinutos = Duration.between(horarioAtual, horarioAtual).toMinutes();
        if (diferencaHorasEmMinutos < 60) {
            throw new ValidacaoExeption("não é possivel agendar consulta com menos de 1h de antecedencia");
        }
    }
}
