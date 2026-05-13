# 🛍️ Qu4troBytes - E-commerce de Camisetas Exclusivas

> Sistema de e-commerce para venda e gestão de camisetas exclusivas, desenvolvido com Java + Spring Boot + MySQL.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Função |
|---|---|---|
| Java | 21+ | Linguagem principal |
| Spring Boot | 3+ | Framework backend |
| Spring Data JPA | - | Integração com banco de dados (ORM) |
| Hibernate | - | Implementação JPA |
| MySQL | 8+ | Banco de dados relacional |
| Maven | - | Gerenciador de dependências |
| BCrypt | - | Criptografia de senhas |

---

## 📋 Funcionalidades

- ✅ Cadastro, edição e exclusão de **Clientes** (com validação de CPF e e-mail)
- ✅ Cadastro e controle de **Produtos** (camisetas) com estoque mínimo
- ✅ Registro de **Vendas** com cálculo automático de valor total
- ✅ Atualização automática de **Estoque** após cada venda
- ✅ **Autenticação** de usuários com senha criptografada (BCrypt)
- ✅ Controle de **perfis de acesso**: ADMIN, VENDEDOR, ESTOQUISTA
- ✅ **Cancelamento de venda** com restauração de estoque

---

## 🏗️ Arquitetura em Camadas

O projeto segue a arquitetura em camadas (Layered Architecture):

```
Requisição HTTP
      ↓
[ CONTROLLER ]   → Recebe a requisição e chama o Service
      ↓
[ SERVICE ]      → Contém as regras de negócio
      ↓
[ REPOSITORY ]   → Acessa o banco de dados via JPA
      ↓
[ DATABASE ]     → MySQL (tabelas: clientes, produtos, vendas, itens_venda, usuarios)
```

---

## 📁 Estrutura de Pastas

```
src/main/java/com/qu4trobytes/
├── QuatrobytesApplication.java   ← ponto de entrada Spring Boot
├── controller/                   ← Endpoints REST (HTTP)
│   ├── ClienteController.java
│   ├── ProdutoController.java
│   ├── VendaController.java
│   └── UsuarioController.java
├── service/                      ← Regras de negócio
│   ├── ClienteService.java
│   ├── ProdutoService.java
│   ├── VendaService.java
│   └── UsuarioService.java
├── model/                        ← Entidades JPA (mapeadas no banco)
│   ├── Cliente.java
│   ├── Produto.java
│   ├── Venda.java
│   ├── ItemVenda.java
│   └── Usuario.java
├── repository/                   ← Interfaces JPA (acesso ao banco)
│   ├── ClienteRepository.java
│   ├── ProdutoRepository.java
│   ├── VendaRepository.java
│   └── UsuarioRepository.java
└── dto/                          ← Objetos de transferência de dados
    ├── ClienteRequestDTO.java
    └── ClienteResponseDTO.java

src/main/resources/
└── application.properties        ← Configuração do banco de dados
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- Java 21 ou superior instalado
- MySQL 8 instalado e rodando
- Maven instalado

### Passo a Passo

**1. Clone o repositório:**
```bash
git clone https://github.com/michaeldouglasx/QuatroBytes.git
cd QuatroBytes
```

**2. Crie o banco de dados no MySQL:**
```sql
CREATE DATABASE IF NOT EXISTS qu4trobytes_ecommerce;
```

**3. Configure o arquivo `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qu4trobytes_ecommerce
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Execute o projeto:**
```bash
mvn spring-boot:run
```

**5. A API estará disponível em:**
```
http://localhost:8080/api
```

---

## 🌐 Endpoints da API

### Clientes
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| POST | `/api/clientes` | Cadastra novo cliente |
| PUT | `/api/clientes/{id}` | Atualiza dados do cliente |
| DELETE | `/api/clientes/{id}` | Remove cliente |

### Produtos
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/produtos` | Lista todos os produtos |
| GET | `/api/produtos/{id}` | Busca produto por ID |
| POST | `/api/produtos` | Cadastra novo produto |
| PUT | `/api/produtos/{id}` | Atualiza produto |
| DELETE | `/api/produtos/{id}` | Remove produto |

### Vendas
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/vendas` | Lista todas as vendas |
| GET | `/api/vendas/{id}` | Busca venda por ID |
| POST | `/api/vendas` | Registra nova venda |
| PUT | `/api/vendas/{id}/cancelar` | Cancela uma venda |

---

## 🗄️ Banco de Dados — Modelo Lógico

```
usuarios          clientes
+---------+       +---------+
| id (PK) |       | id (PK) |
| username|       | nome    |
| senha   |       | cpf     |
| perfil  |       | email   |
| ativo   |       | telefone|
+---------+       +---------+
     |1                 |1
     |                  |
     | registra         | possui
     |N                 |N
     +------+  +--------+
            |  |
         +----------+
         | vendas   |
         +----------+
         | id (PK)  |
         | cliente_id (FK) |
         | usuario_id (FK) |
         | data_venda      |
         | valor_total     |
         | status          |
         +----------+
               |1
               | contém
               |N
  +----------------+    +----------+
  | itens_venda    |    | produtos |
  +----------------+    +----------+
  | id (PK)        |    | id (PK)  |
  | venda_id (FK)  |    | nome     |
  | produto_id (FK)|----| preco    |
  | quantidade     | N:1| estoque  |
  | preco_unitario |    +----------+
  | subtotal       |
  +----------------+
```

---

## 👥 Equipe de Desenvolvimento

| Nome | GitHub |
|---|---|
| Marcelo Alexandre | - |https://github.com/MarceloMarcks
| Michael Douglas | [@michaeldouglasx](https://github.com/michaeldouglasx) |
| Pedro Silva | - | https://github.com/Pedro-Silva-Carvalho | 
| Suel Albuquerque | - | [@suel777](https://github.com/suel777)

---

## 📚 Documentação

A documentação completa de arquitetura e modelagem (requisitos, casos de uso, diagrama de classes, diagrama do banco, código de implementação) está disponível na pasta `/docs` do repositório.

---

*Projeto desenvolvido para a disciplina de Java - SENAC DF*
