package com.ffutagawa.cursos.spring_rest.resource.rest.exception;

import com.ffutagawa.cursos.spring_rest.domain.DetalheErro;
import com.ffutagawa.cursos.spring_rest.exception.IdNaoValidoServiceException;
import com.ffutagawa.cursos.spring_rest.exception.NaoExisteDaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ObjectOptimisticLockingFailureException.class})
    public ResponseEntity<Object> testeDelete(ObjectOptimisticLockingFailureException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("A operação não pôde ser concluída porque o recurso foi modificado ou excluído por outra transação.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);

}

    @ExceptionHandler({IdNaoValidoServiceException.class})
    public ResponseEntity<Object> idInvalido(IdNaoValidoServiceException ex,
                                             WebRequest request) {
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> cpnstrainViolada (org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Constraint Violada: " + ex.getConstraintName())
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.CONFLICT)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({org.hibernate.PropertyValueException.class})
    public ResponseEntity<Object> prorpriedadeNula (org.hibernate.PropertyValueException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("O atributo '"+ex.getPropertyName() + "' não pode ser nulo")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada (NaoExisteDaoException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException (RuntimeException ex, WebRequest request){

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Um exceção foi lançada.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getMethod();
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }



}
