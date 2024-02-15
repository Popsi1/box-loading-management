# Dispatch Management - API Module ![Build Status](https://github.com/MirnaGama/hospital-management-api/actions/workflows/maven.yml/badge.svg)

## About the project
Dispatch Management API built in Spring Boot

### Prerequisites:
- Spring Boot 3.2.2 
- JDK 17
- Maven 4.0.0
- H2 Database
- Swagger documentation

### Running the application
1. `git clone https://github.com/Popsi1/polarisDigitechAssessment.git`
2. `cd polarisDigitechAssessment`
3. `mvn clean install`<br>
It will build the jar file in the target folder
4. `mvn spring-boot:run`<br>
It will compile and run the application on default port (8080)

### Running tests
- `mvn test`<br>
It will executes all the tests.

- `mvn -Dtest=packageName.className test`<br>
It will execute only one test class

- `mvn -Dtest=packageName.className#methodName test`<br>
It will run only one test method from one test class

### Application URLs

Swagger documentation Url - http://localhost:8080/swagger-ui/index.html

H2 Database Console Url - http://localhost:8080/h2-console

H2 Database Username - sa

H2 Database Password - password

:scroll: **END** 

