# API de Simulação de Crédito
Este projeto faz parte do livro **Automação de Testes de API com RestAssured**

# Softwares necessários
Você precisará dos seguintes softwares para executar este aplicação:
* Java JDK 11+
* Maven configurado no seu classpath

# Sobre a aplicação

## Como inicializar a API
Após ter efetuado o clone do projeto:
1. Navegue até a pasta do projeto pelo Terminal / Prompt de Comando
2. Execute o seguinte comando: `mvn spring-boot:run`
3. Aguarde o seguinte texto aparecer: _Aplicação iniciada! Ótimos testes!_
4. Acesse a API através do `http://localhost:8089`

## Como alterar a porta padrão a porta
A porta padrão é a `8089`.
Se você deseja alterar a porta padrão para alguma outra de sua escolha, execute o seguinte comando, substituindo
o texto `<PORTA_DE_SUA_ESCOLHA>` para uma porta não utilizada:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=<PORTA_DE_SUA_ESCOLHA>
```

## Como acessar a documentação (Swagger)
Após ter iniciado a aplicação acesse o seguinte link: http://localhost:8089/swagger-ui.html

# Quer ajudar?
Por favor, leia o [Guia de Contribuição](CONTRIBUTING.md)
