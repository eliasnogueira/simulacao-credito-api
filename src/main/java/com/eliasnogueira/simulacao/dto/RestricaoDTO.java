package com.eliasnogueira.simulacao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Restrição v1")
public class RestricaoDTO {

    @ApiModelProperty(required = true, example = "99999999999")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
