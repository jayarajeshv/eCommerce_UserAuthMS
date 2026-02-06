# ECommerce Project - User Authentication Service

This microservice provides user authentication for the ECommerce platform. It supports user registration, login, JWT-based authentication, OAuth 2.0 and role-based access control.

---

## Features

- User registration with validation
- Secure password hashing using BCrypt
- JWT token generation and validation
- Login endpoint for authentication
- Role-based access for protected resources
- RESTful API design
- Integration with SQL database (MySQL)
- OAuth2.0 compliant

---

## Technologies

- Java 17+
- Spring Web
- Spring Security
- Spring Data JPA
- Maven
- MySQL
- JWT
- OAuth2.0

---
## API Endpoints


| Method | Endpoint                 | Description               |
|--------|--------------------------|---------------------------|
| POST   | `/auth/signup`           | Register a new user       |
| POST   | `/auth/login`            | Login validation for user |
| GET    | `/validate/{tokenValue}` | Validate Token            |

---

## Author
[jayarajeshv](https://github.com/jayarajeshv)

---