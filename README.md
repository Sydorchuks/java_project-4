# Person API – Spring Boot REST Application

Simple Spring Boot web application that manages persons in memory.
The application uses basic POJO classes (`Person`, `Student`, `Teacher`)
and exposes REST endpoints to interact with them.

## Technologies
- Java 21 (runs on newer JVMs as well)
- Spring Boot
- Maven
- Spring Web

No database is used. All data is stored in memory.

## How to run the application

### Using IntelliJ IDEA
1. Open the project in IntelliJ
2. Run `PersonApiApplication`
3. Application will start on a random free port

The exact port is shown in the console, for example:
Tomcat started on port 61665

arduino
Copy code

### Using Maven
If Maven is available:
```bash
mvn spring-boot:run
API Endpoints
Get all persons
bash
Copy code
GET /persons
Add a new person
bash
Copy code
POST /persons


Example request body:

json
Copy code
{
  "name": "Dmytro",
  "age": 22
}
Delete person by id
bash
Copy code
DELETE /persons/{id}
Notes
Data is stored only in memory and resets on application restart

The project is intended for educational purposes

No external plugins or database are required
