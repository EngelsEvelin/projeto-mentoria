# Tutorial do Projeto Helpdesk

## Introdução
Este projeto é um sistema de Helpdesk desenvolvido com Java e Spring Boot. O objetivo é fornecer uma estrutura para gestão de usuários e, futuramente, a implementação de funcionalidades para suporte técnico.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados relacional (ex: PostgreSQL ou MySQL)
- Lombok

## Como Configurar e Rodar o Projeto

1. **Clone o Repositório**
```sh
 git clone https://github.com/seuusuario/helpdesk_mentoria.git
 cd helpdesk_mentoria
```

2. **Configure o Banco de Dados**
   - Certifique-se de ter um banco de dados rodando.
   - Atualize o `application.properties` ou `application.yml` com as credenciais do seu banco de dados.

3. **Instale as Dependências**
```sh
 mvn clean install
```

4. **Execute a Aplicação**
```sh
 mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8081`.

---

## Estrutura do Projeto

### 1. **Camada Controller** (Responsável pelas requisições HTTP)
Arquivo: `UserController.java`
- Define os endpoints para a manipulação de usuários.
- Implementa o endpoint `POST /users` para criar novos usuários.

### 2. **Camada DTO (Data Transfer Object)**
- `CreateUserDto.java`: Estrutura de dados para criar um novo usuário.
- `UserDto.java`: Estrutura de resposta para retorno de usuário.

### 3. **Camada de Domínio (Domain)**
- `User.java`: Representação do usuário com ID, nome, e-mail e data de criação.

### 4. **Camada de Persistência (Entity + Repository)**
- `UserEntity.java`: Mapeia a entidade usuário para o banco de dados.
- `UserRepository.java`: Interface responsável pelo acesso aos dados.

### 5. **Camada de Serviço (Service Layer)**
- `UserService.java`: Contém a regra de negócio para criação de usuários.
- Usa `UserRepository` para salvar os usuários no banco de dados.

---

## Funcionalidades Implementadas
- Cadastro de usuários (`POST /users`).


