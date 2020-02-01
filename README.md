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
3. Aguarde o seguinte texto aparecer: _Started SimulacaoApplication in 6.53 seconds_
4. Acesse a API através do `http://localhost:8089`

## Como alterar a porta padrão a porta
A porta padrão é a `8089`.
Se você dejesa alterar a porta padrão para alguma outra de sua escolha, execute o seguinte comando, substituindo
o texto `<PORTA_DE_SUA_ESCOLHA>` para uma porta não utiliada:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=<PORTA_DE_SUA_ESCOLHA>
```

## Como acessar a documentaçao (Swagger)
Após ter iniciado a aplicação acesse o seguinte link: http://localhost:8089/swagger-ui.html


## Testes em ambiente simulando produção
Esta mesma API está acessível através do seguinte link: https://simulacoes-api.herokuapp.com

Recomendo fortemente que você não execute seus testes repetidamente neste ambiente, pelos seguintes motivos:
* Você não terá o controle da massa dados do retorno de todas as simulação `GET /api/v1/simulacoes`
   * pode ser que alguma outra pessoa tenha inserido mais simulações
* Não é um ambiente controlado por você
   * é uma simulação de um ambiente produtivo

> **ATENÇÃO** 
> Esta aplicação está hospedada no Heroku. Se ela não estiver sendo utilizada por um tempo
> a mesma pode demorar até 30 segundos para responder a primeira requisição
> 
> Você pode conferir se a aplicação está respondendo através do seguinte endpoint
> https://simulacoes-api.herokuapp.com/actuator/health

# Quer ajudar?
Por favor, leia o [Guia de Contribuição](CONTRIBUTING.md)
