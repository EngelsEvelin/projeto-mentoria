# Tutorial: Projeto de Login com Spring Boot

## Introdução
Este projeto implementa um sistema de autenticação usando Spring Boot, Spring Security, JWT e JPA. Ele fornece endpoints para login, registro e proteção de rotas, permitindo a autenticação e autorização de usuários.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- JPA (Java Persistence API) com Hibernate
- H2 para testes
- Lombok
- Maven

## Como Rodar o Projeto Localmente

### Pré-requisitos
- Java 17 instalado
- Maven instalado
- Banco em memória H2

### Passos para execução
1. **Clone o repositório**
   ```sh
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Configure o banco de dados**
   No arquivo `application.properties` ou `application.yml`, configure as credenciais do banco de dados:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Execute o projeto**
   ```sh
   mvn spring-boot:run
   ```

## Endpoints da API

### 1. Registro de Usuário
**Endpoint:** `POST /auth/register`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "123456"
  }
  ```
- **Response:**
  ```json
  {
    "name": "Success",
    "token": "jwt_token"
  }
  ```

### 2. Login de Usuário
**Endpoint:** `POST /auth/login`
- **Request Body:**
  ```json
  {
    "email": "johndoe@example.com",
    "password": "123456"
  }
  ```
- **Response:**
  ```json
  {
    "name": "Success",
    "token": "jwt_token"
  }
  ```

### 3. Obter Dados do Usuário Logado
**Endpoint:** `GET /user`
- **Headers:**
  ```
  Authorization: Bearer jwt_token
  ```
- **Response:**
  ```json
  {
    "message": "sucesso!"
  }
  ```

## Estrutura do Projeto
```
login_app
│── src
│   ├── main
│   │   ├── java/com/example/login_app
│   │   │   ├── controllers
│   │   │   ├── domain/user
│   │   │   ├── dto
│   │   │   ├── infra/cors
│   │   │   ├── infra/security
│   │   │   ├── repositories
│   │   │   ├── services
│   │   ├── resources
│   │   │   ├── application.properties
│── pom.xml
│── README.md
```

## Melhorias Futuras
- Implementar refresh token
- Adicionar roles e permissões para usuários
- Criar testes unitários e de integração
