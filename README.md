# 🚛 CargoPro Backend System

A Spring Boot + PostgreSQL backend application to manage **Load** and **Booking** operations for logistics. Built with clean architecture principles, robust validation, proper status management, and scalable REST APIs.

---

## 📌 Objective

To build a scalable, secure, and testable logistics backend system to manage loads and their associated bookings with status transitions and filtered access.

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- JUnit & Mockito
- Lombok
- Swagger (SpringDoc OpenAPI)
- MapStruct (DTO Mapping)

---

## 🔗 ER Diagram (Simplified)

```text
Load
 └──<has>── Facility (Embedded)
 └──<one-to-many>── Booking
```
## 🔍 API Endpoints
```
##📦 Load APIs
| Method | Endpoint         | Description             |
| ------ | ---------------- | ----------------------- |
| POST   | `/api/load`      | Create a new load       |
| GET    | `/api/load`      | List loads with filters |
| GET    | `/api/load/{id}` | Get load by ID          |
| PUT    | `/api/load/{id}` | Update load             |
| DELETE | `/api/load/{id}` | Delete load             |

## 📑 Booking APIs
| Method | Endpoint            | Description                |
| ------ | ------------------- | -------------------------- |
| POST   | `/api/booking`      | Create a booking           |
| GET    | `/api/booking`      | List bookings with filters |
| GET    | `/api/booking/{id}` | Get booking by ID          |
| PUT    | `/api/booking/{id}` | Update booking             |
| DELETE | `/api/booking/{id}` | Delete booking             |

```
## ✅ Business Rules
Load Status Transitions
On creation → status = POSTED

On booking → status = BOOKED

On booking deletion → status = CANCELLED

On all bookings rejected or deleted → revert status = POSTED

Booking Status Transitions
Cannot book if Load is CANCELLED

On acceptance → ACCEPTED

On deletion → revert Load status logic applied

🔐 Validation & Exception Handling
All requests are validated using @Valid DTOs.

Centralized error handling using @ControllerAdvice.

## 📄 Swagger API Docs
```
Visit: https://swagger.io/solutions/api-documentation/
```
## 🧪 Test Coverage
JUnit 5 & Mockito used

Test coverage > 60%

Test examples:

Service layer unit tests

Controller integration tests

Validation and exception flow

## 1.Clone the repository
```
git clone https://github.com/Dubey123f/cargopro-backend.git
cd cargopro-backend
```
## 2.Set up PostgreSQL
```
## Create database:
CREATE DATABASE cargopro;


## Set PostgreSQL credentials in src/main/resources/application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/cargopro
spring.datasource.username=your_user
spring.datasource.password=your_pass
```
## 3.Run the application
```
./mvnw spring-boot:run

```

##  Folder Structure
```
src/
├── controller/
├── service/
├── repository/
├── dto/
├── model/
├── config/
├── exception/
└── test/

```

