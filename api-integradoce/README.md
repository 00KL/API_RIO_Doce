# Extrator de dados
Extrator de dados relacionados à qualidade de água da bacia do Rio Doce.

## Requisitos
- Stardog Server 6.1.2
- Meaven 3.6.3 

## Como Preparar
- Abra o Eclipse > Import > Existing Maven Project
- Selecione o projeto > Maven > Update
- Aguarde enquanto as dependências são resolvidas

## Como carregar dados no banco
- Abra o Eclipse
- Clique com o botão direito do mouse sobre extratatorIntegraDoce > Run as > Java Aplication

## Como consultar dados no banco via interface web simplificada
- Abra o Eclipse
- Clique com o botão direiro do mouse sobre api-integradoce > Run as > Spring Boot App
- Digite no navegador a url http://localhost:8080/

## Como consultar dados no banco via interface web gerada pelo Swagger
- Abra o Eclipse
- Clique com o botão direiro do mouse sobre api-integradoce > Run as > Spring Boot App
- Digite no navegador a url http://localhost:8080/swagger-ui.html#/