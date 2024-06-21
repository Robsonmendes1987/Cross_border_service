# Documentação da API Cros_Border_Service

## Para clonar o repositório va ate :
https://github.com/Robsonmendes1987/cross_border_service/tree/develop

## Para Rodar a aplicação execute no diretorio cross-border-service$ o comando:
- **mvn spring-boot:run**

## Para testar a API utilizando o Swagger, acesse : http://localhost:8080/swagger-ui/index.html#

## Tecnologias Utilizadas

## O projeto Java utiliza as seguintes tecnologias e dependências:

- **Spring Boot 3.3.0**: Framework para criação de aplicações Java baseadas em Spring.
- **Spring Boot Starter Data JPA**: Facilita o acesso e manipulação de dados usando Hibernate.
- **Spring Boot Actuator**: Fornece endpoints para monitoramento e gerenciamento da aplicação.
- **Spring Boot Validation**: Suporte para validação de dados.
- **Spring Boot Web**: Criação de APIs RESTful.
- **Spring Boot Devtools**: Ferramentas de desenvolvimento para melhorar a experiência do desenvolvedor.
- **H2 Database**: Banco de dados em memória para desenvolvimento e teste.
- **Lombok**: Biblioteca que simplifica o código Java, reduzindo a verbosidade.
- **Spring Boot Starter Test**: Suporte para testes unitários e de integração.
- **Springdoc OpenAPI UI 2.0.4**: Geração automática de documentação OpenAPI (Swagger UI).
- **Jackson Databind**: Serialização e desserialização de objetos JSON.
- **Mockito**: Framework de mocking para testes.


## User API

### Cadastrar Novo Usuário

- **Endpoint:** `POST /api/v1/user/`
- **Corpo da Requisição:**
  ```json
  {
    "nome": "string",
    "email": "string",
    "senha": "string",
    "cpfCnpj": "string"
  }

- **Exemplo de Resposta (200 OK):**

[//]: # (```json)
    {
      "id": 0,
      "nome": "string",
      "email": "string",
      "cpfCnpj": "string",
      "tipoPessoa": "string",
      "createdBY": "string",
      "createdDate": "2024-06-21T02:49:21.298Z",
    }

### Atualizar Cadastro de Usuário

Atualiza o cadastro do usuário com base no ID fornecido.

- **Endpoint:** `PUT /api/v1/user/id/{id}`
- **Parâmetros de URL:**
    - `id`: ID do usuário (integer)
- **Corpo da Requisição:**
  ```json
  {
    "nome": "string",
    "email": "string",
    "senha": "string",
    "cpfCnpj": "string"
  }

- - **Exemplo de Resposta (200 OK)**
  ```json
  {
  "id": 0,
  "nome": "string",
  "email": "string",
  "cpfCnpj": "string",
  "tipoPessoa": "string",
  "createdBY": "string",
  "createdDate": "2024-06-21T02:49:21.298Z",
  }

### Apagar Usuário

- **Endpoint:** `DELETE /api/v1/user/id/{id}`
- **Parâmetros de URL:**
  - `id`: ID do usuário (integer)
- **Exemplo de Resposta (200 OK):**
  ```json
  {
    "message": "Usuário removido com sucesso"
  }


### Listar Todos os Usuários

- **Endpoint:** `GET /api/v1/user/`
- **Exemplo de Resposta (200 OK):**
  ```json
  [
    {
      "id": 0,
      "nome": "string",
      "email": "string",
      "cpfCnpj": "string",
      "tipoPessoa": "string",
      "createdBY": "string",
      "createdDate": "2024-06-21T02:49:21.298Z"
    }
  ]





### Buscar Usuário por ID

- **Endpoint:** `GET /api/v1/user/{id}`
- **Parâmetros de URL:**
  - `id`: ID do usuário (integer)
- **Exemplo de Resposta (200 OK):**
  ```json
  {
    "id": 0,
    "nome": "string",
    "email": "string",
    "cpfCnpj": "string",
    "tipoPessoa": "string",
    "createdBY": "string",
    "createdDate": "2024-06-21T02:49:21.298Z"
  }



### Buscar Usuário por Nome

- **Endpoint:** `GET /api/v1/user/nome/{nome}`
- **Parâmetros de URL:**
  - `nome`: Nome do usuário (string)
- **Exemplo de Resposta (200 OK):**
  ```json
  [
    {
      "id": 0,
      "nome": "string",
      "email": "string",
      "cpfCnpj": "string",
      "tipoPessoa": "string",
      "createdBY": "string",
      "createdDate": "2024-06-21T02:49:21.298Z"
    }
  ]


### Transferir Transação

- **Endpoint:** `POST /api/v1/transaction/transferir`
- **Corpo da Requisição:**
  ```json
  {
    "usuario": 0,
    "destinatario": 0,
    "moeda": "string",
    "valor": 0
  }


- **Exemplo de Resposta (200 OK):**

[//]: # (```json)

{
"message": "Transferência realizada com sucesso"
}





### Listar Transações por Usuário

- **Endpoint:** `GET /api/v1/transaction/listar/{usuarioId}`
- **Parâmetros de URL:**
  - `usuarioId`: ID do usuário (integer)
- **Exemplo de Resposta (200 OK):**
  ```json
  [
    {
      "id": 0,
      "usuario": 0,
      "destinatario": 0,
      "moeda": "string",
      "valor": 0,
      "dataTransacao": "2024-06-21T02:49:21.298Z"
    }
  ]



### Buscar Todas as Cotações

- **Endpoint:** `GET /api/v1/cotacao/todos`
- **Exemplo de Resposta (200 OK):**
  ```json
  [
    {
      "id": 0,
      "createdBy": "string",
      "createdDate": "2024-06-21T02:49:21.298Z",
      "lastModifiedBy": "string",
      "lastModifiedDate": "2024-06-21T02:49:21.298Z",
      "dataCotacao": "2024-06-21",
      "cotacaoCompra": 0,
      "cotacaoVenda": 0
    }
  ]



### Buscar Cotação Atual

- **Endpoint:** `GET /api/v1/cotacao/`
- **Exemplo de Resposta (200 OK):**
  ```json
  {
    "id": 0,
    "createdBy": "string",
    "createdDate": "2024-06-21T02:49:21.298Z",
    "lastModifiedBy": "string",
    "lastModifiedDate": "2024-06-21T02:49:21.298Z",
    "dataCotacao": "2024-06-21",
    "cotacaoCompra": 0,
    "cotacaoVenda": 0
  }


