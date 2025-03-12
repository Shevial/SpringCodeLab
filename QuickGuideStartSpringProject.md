### Create Project

- **Generators:** Place in the project folder

### Dependencies:

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
    - To store data in the database
- **Controller**
    - To transfer data from the database to the view

### CONNECT TO DATABASE

- Add dependencies in `pom.xml`
- Configure database in `application.properties`

### Create classes ...
