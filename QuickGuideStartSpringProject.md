### Create Project

- **Generators:** Place in the project folder

### Add dependencies:

(for example for a tymeleaf with postgreSQL project)

- **Spring Boot Autotools** (dev tools)
- **Spring Web** (web)
- **Thymeleaf** (template engines)
- **Spring Data JPA** (SQL)
- **H2 Database** (SQL)
- **PostgreSQL Driver** (SQL)

### MVC

- **Create packages** (in `com.example`)
    - `model`
    - `repository`
    - `controller`
- **Create class** (inside `model` package)
    - **Book**
        - Attributes
        - Constructors (empty, all parameters)
        - Getters & Setters
        - `toString()`
- **Repository (interface)**
    - **BookRepository**
        - To store data in the database
- **Controller**
    - **BookController**
        - To transfer data from the database to the view

### CONNECT TO DATABASE

- Add dependencies in `pom.xml`
- Configure database in `application.properties`

  **Simple example for connection**

  ```
# Application name in Spring Boot
spring.application.name=TrackEquip

# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/trackequip_db  # Database URL
spring.datasource.username=postgres  # Username
spring.datasource.password=celta  # Password
spring.datasource.driverClassName=org.postgresql.Driver  # JDBC Driver

# JPA/Hibernate configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect  # SQL dialect
spring.jpa.hibernate.ddl-auto=create  # Creates tables on each run
spring.jpa.show-sql=true  # Displays queries in the console

# H2 console for testing (even though we use PostgreSQL)
spring.h2.console.enabled=true  

  ```

  ### Create classes ...
