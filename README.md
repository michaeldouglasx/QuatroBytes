# QuatroBytes — Backend para Gestão Comercial e Controle de Vendas

API REST desenvolvida em **Java 25** com **Spring Boot 4.0.6** para gestão de clientes, produtos e vendas, com autenticação via JWT e controle de acesso por perfil.

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 25 |
| Spring Boot | 4.0.6 |
| Spring Security | 4.0.6 |
| Spring Data JPA | 4.0.6 |
| MySQL | 8.4.0 |
| Auth0 java-jwt | 4.5.2 |
| SpringDoc OpenAPI (Swagger) | 2.8.9 |
| Maven | — |

---

## Pré-requisitos

- Java 25+
- MySQL 8+
- Maven 3.9+

---

## Configuração

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE quatrobytes;
```

Configure o `src/main/resources/application.properties`:

```properties
spring.application.name=QuatroBytes
server.port=8081

# JWT — defina via variável de ambiente JWT_SECRET em produção
api.quatrobytes.security.token.secret=${JWT_SECRET:my-secret-key}

# Banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/quatrobytes
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## Como executar

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/quatrobytes.git
cd quatrobytes/QuatroBytes

# Executar
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8081`.

---

## Usuário administrador padrão

Na primeira execução, o sistema cria automaticamente um ADMIN padrão:

| Campo | Valor |
|---|---|
| Login | `admin` |
| Senha | `123456` |

> **Importante:** crie um novo ADMIN via `POST /api/usuarios` e desative o padrão via `PATCH /api/usuarios/{id}/desativar` antes de ir para produção.

---

## Endpoints

### Autenticação

| Método | Endpoint | Acesso |
|---|---|---|
| POST | `/auth/login` | Público |

### Clientes

| Método | Endpoint | Acesso |
|---|---|---|
| GET | `/api/clientes` | Autenticado |
| GET | `/api/clientes/{id}` | Autenticado |
| POST | `/api/clientes` | Autenticado |
| PUT | `/api/clientes/{id}` | Autenticado |
| DELETE | `/api/clientes/{id}` | Autenticado |

### Produtos

| Método | Endpoint | Acesso |
|---|---|---|
| GET | `/api/produtos` | ADMIN, VENDEDOR, ESTOQUISTA |
| GET | `/api/produtos/{id}` | ADMIN, VENDEDOR, ESTOQUISTA |
| POST | `/api/produtos` | ADMIN, ESTOQUISTA |
| PUT | `/api/produtos/{id}` | ADMIN, ESTOQUISTA |
| DELETE | `/api/produtos/{id}` | ADMIN, ESTOQUISTA |

### Vendas

| Método | Endpoint | Acesso |
|---|---|---|
| POST | `/api/vendas` | ADMIN, VENDEDOR |
| PATCH | `/api/vendas/{id}` | ADMIN, VENDEDOR (cancela a venda) |

### Usuários

| Método | Endpoint | Acesso |
|---|---|---|
| POST | `/api/usuarios` | ADMIN |
| PATCH | `/api/usuarios/{id}/ativar` | ADMIN |
| PATCH | `/api/usuarios/{id}/desativar` | ADMIN |
| PATCH | `/api/usuarios/{id}/alterar-senha` | ADMIN |

---

## Perfis de acesso

| Perfil | Permissões |
|---|---|
| `ADMIN` | Acesso total |
| `VENDEDOR` | Clientes e Vendas |
| `ESTOQUISTA` | Produtos (leitura e escrita) e Clientes |

---

## Documentação interativa (Swagger)

Com a aplicação em execução, acesse:

```
http://localhost:8081/swagger-ui/index.html
```

---

## Estrutura do projeto

```
src/main/java/com/example/QuatroBytes/
├── controller/       # Endpoints REST
├── service/          # Regras de negócio
├── repository/       # Acesso ao banco (JpaRepository)
├── model/            # Entidades JPA e Enums (Perfil, Status)
├── dto/              # Java Records de entrada e saída
│   ├── auth/
│   ├── cliente/
│   ├── produto/
│   ├── venda/
│   ├── ItemVenda/
│   ├── usuario/
│   └── senha/
└── infra/
    ├── security/     # JWT, filtro de autenticação, SecurityConfig
    └── seeder/       # AdminSeeder (cria ADMIN padrão na inicialização)
```

---

## Segurança

- Senhas criptografadas com **BCrypt** (strength 12)
- Autenticação **stateless** via **JWT** com expiração de 2 horas
- Secret configurável via variável de ambiente `JWT_SECRET`
