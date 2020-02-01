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
