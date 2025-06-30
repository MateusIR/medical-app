package med.app.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.app.api.domain.ValidacaoExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrataErro {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataErro404 (){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity trataErro400 (MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoExeption.class)
    public ResponseEntity trataErroNegocio (ValidacaoExeption ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

private record DadosErroValidacao(String mensagem, String campo){
        public DadosErroValidacao(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }
}
