package med.app.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import med.app.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastoMedico dados) {
    medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedicos> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualzarInfoMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInfo(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarInfoMedico(@PathVariable Long id){
        medicoRepository.deleteById(id);
    }


    @Transactional
    public void desativarInfoMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();
    }


}
