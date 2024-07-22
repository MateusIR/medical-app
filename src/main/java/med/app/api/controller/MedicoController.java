package med.app.api.controller;

import jakarta.transaction.Transactional;
import lombok.Getter;
import med.app.api.medico.DadosCadastoMedico;
import med.app.api.medico.Medico;
import med.app.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody DadosCadastoMedico dados) {
    medicoRepository.save(new Medico(dados));
    }
}
