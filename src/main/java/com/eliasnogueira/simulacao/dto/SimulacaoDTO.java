package com.eliasnogueira.simulacao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

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

    public SimulacaoDTO(Long id, String nome, String cpf, String email, BigDecimal valor, Integer parcelas, boolean seguro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }
}
