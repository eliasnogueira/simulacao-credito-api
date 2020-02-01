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

package com.eliasnogueira.simulacao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "cpf_unique", columnNames = "cpf")
})
public class Simulacao {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Nome não pode ser vazio")
    private String nome;

    @NotNull(message = "CPF não pode ser vazio")
    private String cpf;

    @NotNull(message = "E-mail não deve ser vazio")
    @Email
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "E-mail deve ser um e-mail válido")
    private String email;

    @NotNull(message = "Valor não pode ser vazio")
    @Min(value = 1000, message = "Valor deve ser igual ou maior a R$ 1.000")
    @Max(value = 40000, message = "Valor deve ser menor ou igual a R$ 40.000")
    private BigDecimal valor;

    @NotNull(message = "Parcelas não pode ser vazio")
    @Min(value = 2, message = "Parcelas deve ser igual ou maior que 2")
    @Max(value = 48, message = "Parcelas deve ser menor ou igual a 48")
    private Integer parcelas;

    @NotNull(message = "Uma das opções de Seguro devem ser selecionadas")
    private Boolean seguro;
}
