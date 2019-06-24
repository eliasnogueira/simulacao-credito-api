package com.eliasnogueira.simulacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SimulacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimulacaoApplication.class, args);
    }
}
