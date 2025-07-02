package med.app.api.domain.consultas.consultas;

import med.app.api.domain.ValidacaoExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CancelarConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoExeption("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        // Business rule: Cannot cancel an appointment with less than 24 hours' notice
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoExeption("Consulta só pode ser cancelada com antecedência mínima de 24 horas!");
        }

        consulta.cancelar(); 
    }
}