# Projeto Agrix - API Agrícola com Spring Boot e JWT

## Descrição

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot, voltada para o gerenciamento de informações agrícolas, como fazendas, culturas e fertilizantes. A aplicação utiliza autenticação e autorização baseada em JWT (JSON Web Token) para proteger rotas sensíveis, garantindo segurança no acesso aos dados.

## Funcionalidades

- Cadastro e autenticação de usuários (com diferentes perfis: usuário, gerente, administrador)
- Gerenciamento de fazendas (`/farms`)
- Gerenciamento de culturas (`/crops`)
- Gerenciamento de fertilizantes (`/fertilizers`)
- Controle de acesso às rotas conforme o perfil do usuário
- Testes automatizados com JUnit e MockMvc

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven
- JUnit

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- IDE de sua preferência (IntelliJ, Eclipse, VSCode, etc.)

## Instalação e Execução

1. **Clone o repositório:**

```bash
git clone https://github.com/devfelipesantiago/projeto-final-agrix.git
cd java-001-projeto-final-agrix-fase-c
```

2. Instale as dependências e compile o projeto:

```bash
mvn clean install
- Execute a aplicação:
```

```bash
mvn spring-boot:run
```

O servidor estará disponível em <http://localhost:8080> .

## Authorization: Bearer SEU_TOKEN_JWT

A autenticação é feita via JWT. Após o login, um token é gerado e deve ser enviado no header Authorization das requisições protegidas, no formato:

```bash
Authorization: Bearer SEU_TOKEN_JWT
```

### Exemplo de fluxo de autenticação

1. Cadastro de usuário:

   - POST /persons com os dados do usuário.
2. Login:

   - POST /auth/login com username e password .
   - Resposta: { "token": "JWT_TOKEN_AQUI" }
3. Acesso às rotas protegidas:

   - Inclua o token JWT no header das requisições.

### Permissões de acesso

- /farms : acessível para usuários autenticados de qualquer perfil.
- /crops : acessível apenas para gerentes e administradores.
- /fertilizers : acessível apenas para administradores.

## Testes

Para rodar os testes automatizados:

```bash
mvn test
```

## Exemplos de uso das rotas

- Listar fazendas:

```bash
GET /farms
Header: Authorization: Bearer SEU_TOKEN_JWT
```

- Listar consultas:

```bash
GET /crops
Header: Authorization: Bearer SEU_TOKEN_JWT
```

- Listar fertilizantes:

```bash
GET /fertilizers
Header: Authorization: Bearer SEU_TOKEN_JWT
```

## Estrutura do Projeto

```
src/
 └── main/
     └── java/
         └── com/
             └── devfelipesantiago/
                 └── agrix/
 └── test/
     └── java/
         └── com/
             └── devfelipesantiago/
                 └── agrix/
                     └── evaluation/
```
