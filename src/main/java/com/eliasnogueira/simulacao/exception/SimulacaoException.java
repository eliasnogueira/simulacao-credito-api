package com.eliasnogueira.simulacao.exception;

import lombok.Data;

@Data
public class SimulacaoException extends RuntimeException {

    private final String mensagem;

    @Override
    public String toString() {
        return mensagem;
    }
}
