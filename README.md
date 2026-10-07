# 📚 BookFlow

BookFlow is a library management system developed with **Java**, **Spring Boot**, **PostgreSQL**, and **Apache Kafka**.

The project was created as a study and portfolio application with the goal of learning and demonstrating:

- REST APIs
- Spring Boot
- PostgreSQL
- JPA / Hibernate
- DTOs (Representations)
- MapStruct
- Docker
- Apache Kafka
- Event-Driven Architecture
- Asynchronous Processing
- Global Exception Handling
- Validation
- Clean Architecture Principles

---

# 🚀 Features

## Categories

- Create category
- List categories
- Get category by ID
- Update category
- Delete category

---

## Authors

- Create author
- List authors
- Get author by ID
- Update author
- Delete author

---

## Books

- Create book
- List books
- Get book by ID
- Update book
- Delete book

Relationships:

- Book → Author
- Book → Category

---

## Copies (Exemplars)

- Create copy
- List copies
- Get copy by ID
- Delete copy

Each copy belongs to a specific book.

Example:

```text
Book:
Clean Code

Copies:
CC-001
CC-002
CC-003
```

Copy statuses:

```text
AVAILABLE
LOANED
RESERVED
DAMAGED
```

---

## Users

- Create user
- List users
- Get user by ID
- Update user
- Delete user

---

## Loans

- Create loan
- List loans
- Get loan by ID
- Delete loan
- Return loan

Business rules:

- A copy can only be loaned if status = AVAILABLE
- A loan automatically changes the copy status to LOANED
- Returning a loan automatically changes the copy status to AVAILABLE

---

## Reservations

- Create reservation
- List reservations
- Get reservation by ID
- Delete reservation

Business rules:

- Reservations are allowed only for loaned copies
- Duplicate reservations are blocked

---

# 🏗️ Architecture

```text
Controller
    ↓
Service
    ↓
Mapper (MapStruct)
    ↓
Repository
    ↓
PostgreSQL
```

Request Flow:

```text
JSON
↓
Representation (DTO)
↓
Service
↓
Entity
↓
Repository
↓
Database
```

Response Flow:

```text
Database
↓
Entity
↓
Mapper
↓
Representation
↓
JSON
```

---

# 🧩 Technologies

Backend:

- Java 21+
- Spring Boot
- Spring Data JPA
- Spring Validation
- Apache Kafka
- MapStruct
- Lombok

Database:

- PostgreSQL

Infrastructure:

- Docker
- Docker Compose

Tools:

- DBeaver
- Kafka UI
- Postman

---

# 🗄️ Database

Main entities:

```text
Category
Author
Book
Copy (Exemplar)
User
Loan
Reservation
EventHistory
```

---

# 📌 Domain Relationships

```text
Category
    ↑
    |
Book
    |
    ↓
Author
```

```text
Book
    ↓
Copies
```

```text
User
    ↓
Loan
    ↓
Copy
```

```text
User
    ↓
Reservation
    ↓
Copy
```

---

# ⚡ Kafka Architecture

BookFlow uses Kafka to process business events asynchronously.

Current implemented events:

```text
EMPRESTIMO_CRIADO
EMPRESTIMO_DEVOLVIDO
RESERVA_CRIADA
```

---

# Event Flow

## Loan Created

```text
POST /emprestimos
        ↓
EmprestimoService
        ↓
KafkaProducer
        ↓
Topic: emprestimo-criado
        ↓
KafkaConsumer
        ↓
HistoricoEvento
```

---

## Loan Returned

```text
PATCH /emprestimos/{id}/devolver
        ↓
EmprestimoService
        ↓
KafkaProducer
        ↓
Topic: emprestimo-devolvido
        ↓
KafkaConsumer
        ↓
HistoricoEvento
```

---

## Reservation Created

```text
POST /reservas
        ↓
ReservaService
        ↓
KafkaProducer
        ↓
Topic: reserva-criada
        ↓
KafkaConsumer
        ↓
HistoricoEvento
```

---

# 📜 Event History

Every Kafka consumer stores event information into the database.

Example:

```text
LOAN CREATED
LOAN RETURNED
RESERVATION CREATED
```

History endpoint:

```http
GET /historico-eventos
```

Filter by event type:

```http
GET /historico-eventos?tipoEvento=EMPRESTIMO_CRIADO

GET /historico-eventos?tipoEvento=EMPRESTIMO_DEVOLVIDO

GET /historico-eventos?tipoEvento=RESERVA_CRIADA
```

---

# ✅ Validation

Examples:

```java
@NotBlank
@NotNull
@Email
```

Validation errors are handled globally.

Example response:

```json
{
  "timestamp": "2026-09-10T10:00:00",
  "status": 400,
  "message": "Validation Error",
  "errors": {
    "name": "Name is required"
  }
}
```

---

# ❌ Global Exception Handling

The project uses:

```java
@RestControllerAdvice
```

to centralize exception handling.

Examples:

```text
CategoryNotFoundException
AuthorNotFoundException
BookNotFoundException
UserNotFoundException
CopyNotFoundException
LoanNotFoundException
ReservationNotFoundException
```

---

# 🐳 Docker

Infrastructure services run with Docker Compose:

```text
PostgreSQL
Kafka
Kafka UI
```

Current setup:

```text
Spring Boot → IDE

PostgreSQL → Docker

Kafka → Docker

Kafka UI → Docker
```

Start containers:

```bash
docker compose up -d
```

Stop containers:

```bash
docker compose down
```

---

# ⚙️ Application Configuration

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bookflow
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update

    show-sql: true

  kafka:
    bootstrap-servers: localhost:9092
```

> Note:
>
> For educational purposes, database and Kafka configuration are stored directly in `application.yml`.
>
> In production environments, these values should be externalized using environment variables or secret management solutions.

---

# 📂 Project Structure

```text
src
└── main
    └── java
        └── com.wiwu.bookflow

            controller
            service
            repository
            entity
            mapper
            representation
            exception

            kafka
            ├── producer
            ├── consumer
            └── event

            config
```

---

# 📮 Main Endpoints

## Categories

```http
POST   /categorias
GET    /categorias
GET    /categorias/{id}
PUT    /categorias/{id}
DELETE /categorias/{id}
```

## Authors

```http
POST   /autores
GET    /autores
GET    /autores/{id}
PUT    /autores/{id}
DELETE /autores/{id}
```

## Books

```http
POST   /livros
GET    /livros
GET    /livros/{id}
PUT    /livros/{id}
DELETE /livros/{id}
```

## Copies

```http
POST   /exemplares
GET    /exemplares
GET    /exemplares/{id}
DELETE /exemplares/{id}
```

## Users

```http
POST   /usuarios
GET    /usuarios
GET    /usuarios/{id}
PUT    /usuarios/{id}
DELETE /usuarios/{id}
```

## Loans

```http
POST   /emprestimos
GET    /emprestimos
GET    /emprestimos/{id}
DELETE /emprestimos/{id}

PATCH  /emprestimos/{id}/devolver
```

## Reservations

```http
POST   /reservas
GET    /reservas
GET    /reservas/{id}
DELETE /reservas/{id}
```

## Event History

```http
GET /historico-eventos
```

---

# 🎯 Project Goals

This project was created to learn and demonstrate:

- Spring Boot REST APIs
- Database Modeling
- JPA Relationships
- DTO Pattern
- MapStruct
- Validation
- Global Exception Handling
- Docker
- PostgreSQL
- Apache Kafka
- Event-Driven Design
- Asynchronous Processing

---

# 🔮 Future Improvements

Possible future enhancements:

- Authentication & Authorization (JWT)
- Fine Management
- Email Notifications
- Reservation Queue
- Swagger/OpenAPI
- Dockerized Spring Boot Application
- CI/CD Pipeline
- Integration Tests
- Monitoring & Observability

---

# 👨‍💻 Author

William Wu

BookFlow was developed as a study project focused on learning modern backend development and event-driven architectures with Spring Boot and Apache Kafka.
