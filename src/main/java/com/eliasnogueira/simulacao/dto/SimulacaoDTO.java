package com.eliasnogueira.simulacao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "Simulação")
public class SimulacaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(required = true, example = "97093236014",
        notes = "Nome não pode ser vazio")
    private String cpf;

    @ApiModelProperty(position = 1, required = true, example = "João da Silva",
        notes = "CPF não pode ser vazio")
    private String nome;

    @ApiModelProperty(position = 2, required = true, example = "email@email.com",
        notes = "E-mail deve ser um e-mail válido")
    private String email;

    @ApiModelProperty(position = 3, required = true, example = "1200",
        notes = "Valor deve ser igual ou maior a R$ 1.000 e menor ou igual a 40.000")
    private BigDecimal valor;

    @ApiModelProperty(position = 4, required = true, example = "3",
        notes = "Parcelas deve ser igual ou maior que 2 e menor ou igual a 48")
    private Integer parcelas;

    @ApiModelProperty(position = 5, required = true, example = "true")
    private Boolean seguro;
}
