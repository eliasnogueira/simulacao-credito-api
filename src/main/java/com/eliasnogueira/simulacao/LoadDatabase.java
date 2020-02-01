package com.eliasnogueira.simulacao;

import com.eliasnogueira.simulacao.entity.Simulacao;
import com.eliasnogueira.simulacao.repository.SimulacaoRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabaseSimulacao(SimulacaoRepository simulacaoRepository) {
        Simulacao.builder().cpf("").build();

        return args -> {
            simulacaoRepository.save(Simulacao.builder().cpf("66414919004").nome("Fulano").email("fulano@gmail.com")
                .valor(new BigDecimal(11000)).parcelas(3).seguro(true).build());
            simulacaoRepository.save(Simulacao.builder().cpf("17822386034").nome("Deltrano").email("deltrano@gmail.com")
                .valor(new BigDecimal(20000)).parcelas(5).seguro(false).build());
        };
    }
}