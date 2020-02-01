package com.eliasnogueira.simulacao.exception.advice;

import com.eliasnogueira.simulacao.dto.MessageDTO;
import com.eliasnogueira.simulacao.exception.SimulacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SimulacaoAdvice {

    @ResponseBody
    @ExceptionHandler(SimulacaoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageDTO simulacaoHandler(SimulacaoException e) {
        return new MessageDTO(e.toString());
    }
}
