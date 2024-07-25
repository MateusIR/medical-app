package med.app.api.controller;

import jakarta.validation.Valid;
import med.app.api.domain.user.DadosAutenticacao;
import med.app.api.domain.user.User;
import med.app.api.infra.security.DadosTokenJWT;
import med.app.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {


    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DadosAutenticacao dados) {
       var token = new UsernamePasswordAuthenticationToken(dados.user(),dados.password());
       var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
       return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
