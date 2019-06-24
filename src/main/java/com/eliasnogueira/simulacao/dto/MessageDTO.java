package com.eliasnogueira.simulacao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Mensagem")
public class MessageDTO {

    @ApiModelProperty(required = true, example = "O CPF 999999999 não foi encontrado")
    private String mensagem;

    public MessageDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public MessageDTO(){}

    public String getMensagem() {
        return mensagem;
    }
}
