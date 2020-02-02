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

package com.eliasnogueira.simulacao.controller;

import com.eliasnogueira.simulacao.dto.MessageDTO;
import com.eliasnogueira.simulacao.dto.SimulacaoDTO;
import com.eliasnogueira.simulacao.dto.ValidacaoDTO;
import com.eliasnogueira.simulacao.entity.Simulacao;
import com.eliasnogueira.simulacao.exception.SimulacaoException;
import com.eliasnogueira.simulacao.repository.SimulacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/simulacoes", tags = "Simulações")
public class SimulacaoController {

    private final SimulacaoRepository repository;
    private static final String CPF_NAO_ENCONTRADO = "CPF {0} não encontrado";

    public SimulacaoController(SimulacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/v1/simulacoes")
    @ApiOperation(value = "Retorna todas as simulações existentes")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Simulações encontradas", response = SimulacaoDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Nome não encontrado")
    })
    List<Simulacao> getSimulacao(@RequestParam(name = "nome", required = false) String nome) {
        List<Simulacao> simulacoesEncontradas;

        Example<Simulacao> example =
            Example.of(Simulacao.builder().nome(nome).build(),
                ExampleMatcher.matchingAny().
                    withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains()));

        simulacoesEncontradas = repository.findAll(example);

        if (simulacoesEncontradas.isEmpty()) {
            throw new SimulacaoException("Nome não encontrado");
        }

        return simulacoesEncontradas;
    }

    @GetMapping("/api/v1/simulacoes/{cpf}")
    @ApiOperation(value = "Retorna uma simulação através do CPF")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Simulação encontrada", response = SimulacaoDTO.class),
        @ApiResponse(code = 404, message = "Simulação não encontrada", response = MessageDTO.class)
    })
    ResponseEntity<Simulacao> one(@PathVariable String cpf) {
        return repository.findByCpf(cpf).
            map(simulacao -> ResponseEntity.ok().body(simulacao)).
            orElseThrow(() -> new SimulacaoException(MessageFormat.format(CPF_NAO_ENCONTRADO, cpf)));
    }

    @PostMapping("/api/v1/simulacoes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere uma nova simulação", code = 201)
    @ApiResponses({
        @ApiResponse(code = 201, message = "Simulação criada com sucesso", response = SimulacaoDTO.class),
        @ApiResponse(code = 400, message = "Falta de informações", response = ValidacaoDTO.class),
        @ApiResponse(code = 409, message = "CPF já existente")
    })
    Simulacao novaSimulacao(@Valid @RequestBody SimulacaoDTO simulacao) {
        return repository.save(new ModelMapper().map(simulacao, Simulacao.class));
    }

    @PutMapping("/api/v1/simulacoes/{cpf}")
    @ApiOperation(value = "Atualiza uma simulação existente através do CPF")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Simulação alterada com sucesso", response = SimulacaoDTO.class),
        @ApiResponse(code = 404, message = "Simulação não encontrada", response = MessageDTO.class),
        @ApiResponse(code = 409, message = "CPF já existente")
    })
    Simulacao atualizaSimulacao(@RequestBody SimulacaoDTO simulacao, @PathVariable String cpf) {
        return update(new ModelMapper().
            map(simulacao, Simulacao.class), cpf).
            orElseThrow(() -> new SimulacaoException(MessageFormat.format(CPF_NAO_ENCONTRADO, cpf)));
    }

    @DeleteMapping("/api/v1/simulacoes/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove uma simulação existente através do CPF", code = 204)
    @ApiResponses({
        @ApiResponse(code = 204, message = "Simulação removida com sucesso"),
        @ApiResponse(code = 404, message = "Simulação não encontrada", response = MessageDTO.class)
    })
    void delete(@PathVariable String cpf) {
        if (repository.findByCpf(cpf).isEmpty()) {
            throw new SimulacaoException(MessageFormat.format(CPF_NAO_ENCONTRADO, cpf));
        } else {
            repository.deleteByCpf(cpf);
        }
    }

    private Optional<Simulacao> update(Simulacao novaSimulacao, String cpf) {
        return repository.findByCpf(cpf).map(simulacao -> {
            setIfNotNull(simulacao::setId, novaSimulacao.getId());
            setIfNotNull(simulacao::setNome, novaSimulacao.getNome());
            setIfNotNull(simulacao::setCpf, novaSimulacao.getCpf());
            setIfNotNull(simulacao::setEmail, novaSimulacao.getEmail());
            setIfNotNull(simulacao::setParcelas, novaSimulacao.getParcelas());
            setIfNotNull(simulacao::setValor, novaSimulacao.getValor());

            return repository.save(simulacao);
        });
    }

    private <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
