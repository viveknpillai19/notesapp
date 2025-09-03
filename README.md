Spring Boot Notes API
A simple yet robust RESTful API for creating and managing user notes, built with Java and the Spring Boot framework. This project demonstrates core backend concepts including layered architecture, database persistence with JPA, file I/O operations, and unit testing.

Features
User Management: Create new users.

Note Management: Create notes that are linked to specific users.

Data Persistence: All users and notes are saved to a PostgreSQL database.

File Archiving: Every new note is automatically appended to a notes.txt log file.

Data Retrieval: Fetch all notes belonging to a specific user. The API reads from both the database and the log file to enrich the response.

Unit Tested: Includes a unit test for the core service logic.

Technologies Used
Java 17

Spring Boot 3

Spring Web: For building RESTful APIs.

Spring Data JPA: For database interaction.

Hibernate: JPA implementation.

Spring Security: For securing endpoints.

PostgreSQL: Relational database for data storage.

Lombok: To reduce boilerplate code.

Maven: For project build and dependency management.

JUnit 5 & Mockito: For unit testing.

Setup and Installation
Follow these steps to get the application running locally.

Prerequisites
Java Development Kit (JDK) 17 or later.

Apache Maven.

A running instance of PostgreSQL.

Steps
Clone the repository:

git clone <your-repository-url>
cd notesapp

Create a PostgreSQL Database:
Create a new database for the application. You can name it notesdb or any other name you prefer.

Configure the Application:
Open the src/main/resources/application.properties file and update the database connection details to match your local PostgreSQL setup:

# PostgreSQL Connection Settings
spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password

# Instruct Hibernate to create/update tables automatically
spring.jpa.hibernate.ddl-auto=update

How to Run
You can run the application using the Spring Boot Maven plugin:

mvn spring-boot:run

The application will start and be accessible at http://localhost:8080.

API Endpoints
The following endpoints are available:

Method

Endpoint

Description

Request Body Example

POST

/api/users/add

Creates a new user.

{"username": "testuser", "password": "password123"}

POST

/api/notes/add

Creates a new note for an existing user.

{"title": "My Note", "content": "Hello world!", "user": {"id": 1}}

GET

/api/notes/{userId}

Retrieves all notes for a specific user.

(None)

How to Test
To run the included unit tests, execute the following Maven command:

mvn test
