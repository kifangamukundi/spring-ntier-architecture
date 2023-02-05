# Spring Boot N-Tier Architecture

* This project showcases how to structure a spring boot api application
* It includes the right package structure that makes it easier to extend for future applications

## Structure
* Resource or Controller Later - Is the first entry point to the application that allows access to all services
* Service Layer - Is an interface that defines specifications that a given implementation should adhere to
* Implementation or Business Layer - It implements the service layer interface and handles on the business specific objectives and communication with other services
* Model or Domain Layer - maps fields and properties stored in a database
* Repository or Data Layer - Is used for managing data in a spring boot application