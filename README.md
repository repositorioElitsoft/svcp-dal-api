# SVCP Data Access Layer API (svcp-dal-api)

This is a Spring Boot-based RESTful API that serves as data access layer project. 
It integrates with PostgreSQL for data storage, uses MyBatis for ORM, and implements security using Spring Security and JWT (JSON Web Tokens). 
The project also includes OpenAPI for API documentation, Lombok for reducing boilerplate code, MapStruct for object mapping, and Logback for logging.

## Technologies Used

- **Spring Boot**: Core framework for building the application.
- **Spring Security**: Security framework for authentication and authorization.
- **JWT (JSON Web Tokens)**: Token-based authentication for securing endpoints.
- **PostgreSQL**: Relational database for data storage.
- **MyBatis**: Object-Relational Mapping (ORM) framework for database interactions.
- **Maven**: Build automation and dependency management.
- **Lombok**: Library to reduce boilerplate code (e.g., getters, setters).
- **MapStruct**: Object mapping library for converting DTOs to entities and vice versa.
- **Logback**: Logging framework for application logging.
- **OpenAPI (Swagger)**: API documentation and testing.
- **Java 21**: Programming language used for development.


## Features

- RESTful API endpoints for CRUD operations.
- Secure endpoints using JWT-based authentication.
- Database integration with PostgreSQL using MyBatis.
- OpenAPI documentation for easy API exploration.
- Logging with Logback for better debugging and monitoring.
- Object mapping with MapStruct for clean and efficient code.
- Lombok for reducing boilerplate code.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 21**: JDK 21 or higher.
- **Maven**: Apache Maven 3.6.x or higher.
- **PostgreSQL**: PostgreSQL 12 or higher.
- **Docker** (optional): For running PostgreSQL in a container.

## Build the Project
 
 	mvn clean install

## Run the Application
  
	mvn spring-boot:run

## Access OpenAPI Documentation

	http://localhost:8080/swagger-ui.html


## Project Structure

The project is organized into the following structure:


```bash
src/main/java
├── com.elitsoft.servicampo
│ ├── config # Configuration classes (e.g., SecurityConfig, MyBatisConfig)
│ ├── controller # REST controllers
│ │ ├── core # Controllers for core functionality
│ │ └── mobile # Controllers for mobile-specific functionality
│ ├── domain # Domain layer (entities and DTOs)
│ │ ├── dto # Data Transfer Objects (DTOs)
│ │ │ ├── core # Core DTOs
│ │ │ └── mobile # Mobile-specific DTOs
│ │ └── entity # Database entities
│ ├── exception # Custom exceptions
│ ├── mapstruct # MapStruct mappers for object mapping
│ ├── mapper # MyBatis mappers (repositories) for database operations
│ ├── security # Spring Security and JWT-related classes
│ ├── service # Business logic and service layer
│ │ ├── core # Services for core functionality
│ │ └── mobile # Services for mobile-specific functionality
│ └── util # Utility classes
src/main/resources
├── mappers # MyBatis XML files for SQL queries and CRUD
├── application.yml # Application configuration
├── logback-spring.xml # Logback configuration
```

### Explanation of Key Directories:
- **`config`**: Contains configuration classes for Spring Boot, MyBatis, and Spring Security.
- **`controller`**: Houses REST controllers that handle incoming HTTP requests.
  - **`core`**: Controllers for core functionality.
  - **`mobile`**: Controllers for mobile-specific functionality.
- **`domain`**: Contains the domain layer, including entities and DTOs.
  - **`dto`**: Data Transfer Objects (DTOs) for request/response payloads.
    - **`core`**: Core DTOs for core functionality.
    - **`mobile`**: Mobile-specific DTOs for mobile functionality.
  - **`entity`**: Database entities mapped to PostgreSQL tables.
- **`exception`**: Custom exception classes for error handling.
- **`mapstruct`**: MapStruct interfaces for object mapping between entities and DTOs.
- **`mapper`**: MyBatis mapper interfaces and XML files for database operations.
- **`security`**: Contains Spring Security configurations, JWT utilities, and authentication logic.
- **`service`**: Implements the business logic and service layer.
  - **`core`**: Services for core functionality.
  - **`mobile`**: Services for mobile-specific functionality.
- **`util`**: Utility classes and helper methods.

- **`mappers`**: Contains MyBatis `.xml` files with SQL queries for database operations.
- **`application.yml`**: Configuration file for Spring Boot (database, server, etc.).
- **`logback-spring.xml`**: Logback configuration for logging.





