/*
 * MIT License
 *
 * Copyright (c) today.year Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
