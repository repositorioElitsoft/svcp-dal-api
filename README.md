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

└── src
    ├── main
    │  ├── java
    │  │  └── com
    │  │      └── elitsoft
    │  │          └── servicampo		 # Proyect Name
    │  │              ├── config		 # Configuration classes (e.g., SecurityConfig, MyBatisConfig)
    │  │              ├── controller     # REST controllers
    │  │              │  ├── core
    │  │              │  └── mobile
    │  │              ├── domain
    │  │              │  ├── dto         # Data Transfer Objects (DTOs)
    │  │              │  │  ├── core
    │  │              │  │  └── mobile
    │  │              │  └── entity      # Database entities
    │  │              ├── exceptions 	 # Custom exceptions
    │  │              ├── mapper 		 # MyBatis mappers (repositories)
    │  │              ├── mapstruct      # MapStruct mappers
    │  │              ├── security 		 # Spring Security and JWT-related classes
    │  │              ├── service		 # Business logic and service layer
    │  │              │  ├── core
    │  │              │  └── mobile
    │  │              └── utils
    │  └── resources
    │      └── mappers					# MyBatis .xml files with CRUD sentences (SQL)
       └── test
        └── java
            └── com
                └── elitsoft
                    └── servicampo		# Proyect Name







