# SVCP Data Access Layer API (svcp-dal-api)

Esta es una API RESTful basada en Spring Boot que sirve como la capa de acceso a datos del proyecto.
Se integra con PostgreSQL para el almacenamiento de datos, utiliza MyBatis como ORM e implementa seguridad mediante Spring Security y JWT (JSON Web Tokens).

El proyecto también incluye OpenAPI para la documentación de la API, Lombok para reducir el código repetitivo, MapStruct para el mapeo de objetos, Logback para 
el registro de logs (logging) y Spring Boot Actuator para la supervisión y gestión de la aplicación.

## Tecnologías Utilizadas

- **Spring Boot**: Marco principal para construir la aplicación.
- **Spring Security**: Marco de seguridad para autenticación y autorización.
- **Spring Boot Actuator**: Proporciona funciones listas para producción para supervisar y gestionar la aplicación.
- **JWT (JSON Web Tokens)**: Autenticación basada en tokens para asegurar los endpoints.
- **PostgreSQL**: Base de datos relacional para el almacenamiento de datos.
- **MyBatis**: Marco de Mapeo Objeto-Relacional (ORM) para interacciones con la base de datos.
- **Lombok**: Biblioteca para reducir el código repetitivo (por ejemplo, getters, setters).
- **MapStruct**:  Biblioteca de mapeo de objetos para convertir DTOs en entidades y viceversa.
- **Logback**: Marco de registro de logs (logging) para la aplicación.
- **OpenAPI (Swagger)**: Documentación y pruebas de la API.
- **Maven**: Automatización de la construcción y gestión de dependencias.
- **Java 21**: Lenguaje de programación utilizado para el desarrollo.


## Features

- Endpoints RESTful para operaciones CRUD.
- Endpoints seguros utilizando autenticación basada en JWT.
- Integración con PostgreSQL usando MyBatis.
- Documentación OpenAPI para una fácil exploración de la API.
- Registro de logs con Logback para una mejor depuración y monitoreo.
- Mapeo de objetos con MapStruct para un código limpio y eficiente.
- Lombok para reducir el código repetitivo.
- Spring Boot Actuator para verificaciones de salud, métricas y monitoreo.

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java 21**: JDK 21 o superior.
- **Maven**: Apache Maven 3.6.x o superior.
- **PostgreSQL**: PostgreSQL 12 o superior.
- **Docker** Docker (opcional): Para ejecutar PostgreSQL en un contenedor.

## Construir el Proyecto
 
 	mvn clean install

## Ejecutar la Aplicación
  
	mvn spring-boot:run

## Acceder a la Documentación de OpenAPI

	http://localhost:8080/swagger-ui.html

## Acceder a los Endpoints de Actuator

Spring Boot Actuator proporciona varios endpoints para supervisar y gestionar la aplicación. Por defecto, están disponibles bajo la ruta ```/actuator``` Por ejemplo:

- Verificación de salud: ```http://localhost:8080/actuator/health```

- Métricas:  ```http://localhost:8080/actuator/metrics ```

- Detalles del entorno:  ```http://localhost:8080/actuator/env ```

## Estructura del Proyecto

El proyecto está organizado en la siguiente estructura:


```bash
src/main/java
├── com.elitsoft.servicampo
│  ├── config # Clases de configuración (por ejemplo, SecurityConfig, MyBatisConfig)
│  ├── controller # Controladores REST
│  │ ├── core # Controladores para la funcionalidad principal
│  │ └── mobile # Controladores para la funcionalidad específica de móviles
│  ├── domain # Capa del dominio (entidades y DTOs)
│  │ ├── dto # Objetos de Transferencia de Datos (DTOs)
│  │ │ ├── core # DTOs principales
│  │ │ └── mobile # DTOs específicos para móviles
│  │ └── entity # Entidades de la base de datos
│  ├── exception # Excepciones personalizadas
│  ├── mapstruct # Mapeadores de MapStruct para el mapeo de objetos
│  ├── mapper # Mapeadores de MyBatis (repositorios) para operaciones de base de datos
│  ├── security # Clases relacionadas con Spring Security y JWT
│  ├── service # Lógica de negocio y capa de servicios
│  │ ├── core # Servicios para la funcionalidad principal
│  │ └── mobile # Servicios para la funcionalidad específica de móviles
│  └── util # Clases de utilidad
src/main/resources
├── mappers # Archivos XML de MyBatis para consultas SQL y operaciones CRUD
├── application.yml # Configuración de la aplicación
├── logback-spring.xml # Configuración de Logback
```

### Explicación de los Directorios Clave:
- **`config`**: Contiene clases de configuración para Spring Boot, MyBatis y Spring Security.
- **`controller`**: Alberga los controladores REST que manejan las solicitudes HTTP entrantes.
  - **`core`**: Controladores para la funcionalidad principal.
  - **`mobile`**: Controladores para la funcionalidad específica de móviles.
- **`domain`**: Contiene la capa del dominio, incluyendo entidades y DTOs.
  - **`dto`**: Objetos de Transferencia de Datos (DTOs) para los payloads de solicitud/respuesta.
    - **`core`**: DTOs principales para la funcionalidad central.
    - **`mobile`**: DTOs específicos para la funcionalidad móvil.
  - **`entity`**: Entidades de la base de datos mapeadas a tablas de PostgreSQL.
- **`exception`**: Clases de excepciones personalizadas para el manejo de errores.
- **`mapstruct`**: Interfaces de MapStruct para el mapeo de objetos entre entidades y DTOs.
- **`mapper`**: Interfaces de mapeo de MyBatis y archivos XML para operaciones de base de datos.
- **`security`**: Contiene configuraciones de Spring Security, utilidades de JWT y lógica de autenticación.
- **`service`**:  Implementa la lógica de negocio y la capa de servicios.
  - **`core`**: Servicios para la funcionalidad principal.
  - **`mobile`**: Servicios para la funcionalidad específica de móviles.
- **`util`**: Clases de utilidad y métodos auxiliares.

- **`mappers`**: Contiene archivos `.xml` de MyBatis con consultas SQL para operaciones de base de datos.
- **`application.yml`**: Archivo de configuración para Spring Boot (base de datos, servidor, etc.).
- **`logback-spring.xml`**: Configuración de Logback para el registro de logs (logging).





