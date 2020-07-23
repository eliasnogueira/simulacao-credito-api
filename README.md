# API de Simulação de Crédito
Este projeto faz parte do livro **Automação de Testes de API com RestAssured**

# Softwares necessários
Você precisará dos seguintes softwares para executar esta aplicação:
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
Após ter iniciado a aplicação acesse o seguinte link: http://localhost:8089/swagger-ui/index.html

## Dependência da API de Restrições
Este projeto usa a [API de Restrições](https://github.com/eliasnogueira/restricao-credito-api) quando cria ou atualiza uma simulação, logo você precisá executar também 
esta API para poder utilizar este projeto.

As informações de conexão estão disponíveis no arquivo `src\main\resources\application.properties`, sendo os valores default:

```properties
service.restricao.baseurl=http://localhost
service.restricao.port=8088
service.restricao.endpoint=/api/v1/restricoes
```

Caso você esteja executando a API de Restrições numa máquina ou porta diferente, por favor, atualize os valores no 
arquivo antes de iniciar a API de Simulações.

# Quer ajudar?
Por favor, leia o [Guia de Contribuição](CONTRIBUTING.md)
