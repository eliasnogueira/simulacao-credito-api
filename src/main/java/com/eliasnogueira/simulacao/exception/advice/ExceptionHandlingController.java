package com.eliasnogueira.simulacao.exception.advice;

import com.eliasnogueira.simulacao.dto.MessageDTO;
import com.eliasnogueira.simulacao.dto.ValidacaoDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<ValidacaoDTO> handleConstraintViolation(TransactionSystemException ex) {

        if (ex.getCause() instanceof RollbackException) {
            RollbackException rollbackException = (RollbackException) ex.getCause();
            if (rollbackException.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) rollbackException.getCause();

                Map<String, String> errors = new HashMap<>();
                constraintViolationException.getConstraintViolations().forEach(constraintViolation ->
                        errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));

                return new ResponseEntity<>(new ValidacaoDTO(errors), HttpStatus.BAD_REQUEST);
            }
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Handler para apresentar a mensagem customizada de duplicidade de CPF
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageDTO> dataIntegriyException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) ex.getCause();

            if (constraintViolationException.getSQLException().getMessage().contains("CPF")) {
                return new ResponseEntity<>(new MessageDTO("CPF duplicado"), HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok().build();
    }
}
