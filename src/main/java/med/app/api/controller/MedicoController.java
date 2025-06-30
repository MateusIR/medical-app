package med.app.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.app.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastoMedico dados, UriComponentsBuilder uriBuilder) {
    var medico = new Medico(dados);
    medicoRepository.save(medico);
    var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
    return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>>listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualzarInfoMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInfo(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarInfoMedico(@PathVariable Long id){
        medicoRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }


    @Transactional
    public ResponseEntity desativarInfoMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){

        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }


}
