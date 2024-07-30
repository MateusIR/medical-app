package med.app.api.domain.consultas.consultas.validacao;

import med.app.api.domain.ValidacaoExeption;
import med.app.api.domain.consultas.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioFuncionamento  implements ValidacoesConsulta{



    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var dataConsulta = dadosAgendamentoConsulta.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesFuncionamento = dataConsulta.getHour() < 6;
        var depoisFuncionamento = dataConsulta.getHour() < 19;

        if(domingo || antesFuncionamento || depoisFuncionamento){
            throw new ValidacaoExeption("horario de funcionamento não disponivel");
        }
    }

}
