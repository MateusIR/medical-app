package med.app.api.controller;

import jakarta.validation.Valid;
import med.app.api.domain.consultas.consultas.AgendarConsultas;
import med.app.api.domain.consultas.consultas.DadosAgendamentoConsulta;
import med.app.api.domain.consultas.consultas.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendarConsultas agendarConsultas;


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendarConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }
}
