

# Desafio Magalu - Notificação
Este projeto foi desenvolvido como parte do desafio da Magalu, com o objetivo de criar um sistema para gerenciamento e envio de notificações. O sistema é construído utilizando **Java**, **Spring Boot**, **JPA**, **Docker** e **Scheduled Tasks**.

## Tecnologias Utilizadas

- **Java 17** (ou superior)
- **Spring Boot**
- **JPA (Java Persistence API)**
- **MySQL**
- **Docker**
- **Docker Compose**
- **Spring Scheduled**

## Funcionalidades

Este sistema permite que o usuário:

1. **Cadastrar uma notificação** com uma data e hora programada para envio.
2. **Consultar as notificações** por ID.
3. **Deletar notificações** pelo ID.

Além disso, a aplicação possui uma tarefa agendada (Scheduled) que verifica as notificações com status "PENDING" a cada minuto e envia as notificações programadas.

## Endpoints da API

### 1. **Salvar Notificação**
- **URL:** `http://localhost:8080/notifications`
- **Método:** `POST`
- **Corpo da Requisição (JSON):**
    ```json
    {
        "dateTime": "2025-03-07T21:10:30",
        "destination": "teste",
        "message": "teste",
        "channel": "EMAIL"
    }
    ```
- **Resposta (Status 202 - accepted):**
    Nenhum corpo na resposta.

  
### 2. **Consultar Notificação por ID**
- **URL:** `http://localhost:8080/notifications/{id}`
- **Método:** `GET`
- **Resposta (Status 200 - OK):**
    ```json
    {
        "notificationid": 1,
        "dateTime": "2024-06-26T14:56:30",
        "destination": "teste",
        "message": "teste",
        "channel": {
            "channelId": 1,
            "description": "email"
        },
        "status": {
            "statusId": 4,
            "description": "canceller"
        }
    }
    ```

### 3. **Deletar Notificação por ID**
- **URL:** `http://localhost:8080/notifications/{id}`
- **Método:** `DELETE`
- **Resposta (Status 204 - No Content):**
    Nenhum corpo na resposta.

## Funcionalidade de Envio de Notificação

A aplicação salva notificações com um horário de envio especificado pelo usuário. O sistema verifica periodicamente as notificações com status "PENDING" e envia as notificações programadas, utilizando o recurso **Scheduled** do Spring.

### Como Funciona:

1. O usuário envia uma notificação com uma data e hora programada.
2. O sistema armazena a notificação no banco de dados.
3. A cada minuto, o **Scheduled Task** verifica se alguma notificação está com o status "PENDING" e se a hora de envio foi atingida.
4. Caso a notificação seja enviada com sucesso, o status é alterado para "SUCCESS". Caso contrário, o status é alterado conforme o erro.

## Como Rodar a Aplicação

### 1. **Docker e Docker Compose**

Este projeto utiliza **Docker** para rodar o banco de dados MySQL. O `docker-compose.yml` está configurado para levantar o contêiner do MySQL.

#### Passo 1: Subir o Docker Compose

Na pasta "docker" do projeto, execute o seguinte comando para subir o MySQL da aplicação:

```bash
docker-compose up 
```

Isso irá construir e iniciar os contêineres necessários.

#### Passo 2: Rodar a Aplicação

A aplicação será executada na porta `8080`. Você pode acessar a API através de um cliente HTTP (como o **Postman** ou **Insomnia**) ou diretamente via navegador.

### 2. **Requisitos**

- **Java 21+**
- **Docker** (para rodar o banco de dados MySQL)
- **Docker Compose**


## Considerações Finais

Este projeto oferece uma implementação simples de gerenciamento de notificações, com funcionalidades de cadastro, consulta e exclusão de notificações. A tarefa agendada (Scheduled Task) permite que o sistema envie as notificações automaticamente com base no horário especificado.
