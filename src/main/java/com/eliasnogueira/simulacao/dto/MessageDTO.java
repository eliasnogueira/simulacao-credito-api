package com.eliasnogueira.simulacao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "Mensagem")
public class MessageDTO {

    @ApiModelProperty(required = true, example = "O CPF 999999999 não foi encontrado")
    private String mensagem;
}
