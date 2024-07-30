package med.app.api.domain.consultas.consultas;

import jakarta.validation.Valid;
import med.app.api.domain.ValidacaoExeption;
import med.app.api.domain.consultas.consultas.validacao.ValidacoesConsulta;
import med.app.api.domain.medico.Medico;
import med.app.api.domain.medico.MedicoRepository;
import med.app.api.domain.paciente.Paciente;
import med.app.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AgendarConsultas {


    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesConsulta> validacoesConsultaList;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosConsulta) {

        if (!pacienteRepository.existsById(dadosConsulta.idPaciente())){
            throw new ValidacaoExeption("Id do paciente não existe");
        }

        if ( dadosConsulta.idMedico() != null && !medicoRepository.existsById(dadosConsulta.idMedico())){
            throw new ValidacaoExeption("Id do medico não existe");
        }

        validacoesConsultaList.forEach(validacoesConsulta -> validacoesConsulta.validar(dadosConsulta));

        var paciente = pacienteRepository.findById(dadosConsulta.idPaciente()).get();
        var medico = escolheMedico(dadosConsulta);
        if (medico == null) {
            throw new ValidacaoExeption("medicos não disponiveis nessa data");
        }
        var consulta = new Consulta( null, medico, paciente, dadosConsulta.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolheMedico(DadosAgendamentoConsulta dadosConsulta) {
     if (dadosConsulta.idMedico() != null){
         return medicoRepository.findById(dadosConsulta.idMedico()).get();
     }
if (dadosConsulta.especialidade() == null){
    throw new ValidacaoExeption("especialicade não preenchida");

}
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosConsulta.especialidade(), dadosConsulta.data());
    }


}
