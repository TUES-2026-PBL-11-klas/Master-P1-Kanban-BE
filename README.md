# Master-P1-Kanban-BE

Backend repository for the Kanban board project developed by Team Master as part of the second-term PBL.

---

## Project Overview

This project is a REST backend service that manages users and tasks in a Kanban board system.
It provides APIs for creating, updating, deleting, and filtering tasks based on their state and owner.

The backend is designed to be used by a frontend application and communicates using JSON over HTTP.

---

## Features

* User creation and management
* Task creation and soft deletion
* Filtering tasks by:
    * User
    * State
* Task state tracking:
    * Newly created
    * Work in progress
    * Finished
* REST API
* Layered architecture (Controller, Service, Repository)
* Unit testing with Mockito and JUnit

---

## Technologies used

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* JUnit 5
* Mockito
* PostgreSQL

---

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/TUES-2026-PBL-11-klas/Master-P1-Kanban-BE.git
cd Master-P1-Kanban-BE/Kanban
```

### 2. Configure the database

Update the database configuration in:

```
deployment/.env
```

Make sure the database is running and credentials are correct.

### 3. Build the project

On Linux / macOS:
```bash
./mvnw clean install
```
On Windows:
```bash
.\mvnw clean install
```
Or
```bash
mvnw clean install
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The backend will start on:

```
http://localhost:8085
```

---

## 📡 API Endpoints

### Users

| Method | Endpoint        | Description       |
| ------ | --------------- | ----------------- |
| POST   | `/api/v1/users` | Create a new user |

### Tasks

| Method | Endpoint                                      | Description                 |
| ------ | --------------------------------------------- | --------------------------- |
| GET    | `/api/v1/tasks/user/{userId}`                 | Get all tasks for a user    |
| GET    | `/api/v1/tasks/{taskId}`                      | Get a task by ID            |
| POST   | `/api/v1/tasks`                               | Create a new task           |
| PUT    | `/api/v1/tasks`                               | Update a task               |
| DELETE | `/api/v1/tasks/{taskId}`                      | Soft delete a task          |
| GET    | `/api/v1/tasks/user/{userId}/state/{stateId}` | Get tasks filtered by state |

---

## Project Structure

### Controller

Handles HTTP requests and responses.

#### `controller/APIController.java`

Exposes REST endpoints and delegates logic to the service layer. Converts Java objects to JSON and vice versa.

---

### Model

#### `State.java`

Represents task states:

* Newly created
* Work in progress
* Finished

#### `Task.java`

Core domain entity.
Contains:

* id
* user
* deleted flag
* title
* description
* priority
* state
* creation timestamp

#### `User.java`

Represents a system user with:

* id
* username
* list of tasks

---

### Repository

Interfaces that interact with the database using Spring Data JPA.

* TaskRepository
* UserRepository
* StateRepository

They provide CRUD operations and custom queries.

---

### Service

#### `KanbanService.java`

Defines the business operations:

* Create user
* Manage tasks
* Filtering and retrieval

#### `KanbanServiceImplementation.java`

Implements the business logic and communicates with the repositories.

---

### Application

#### `KanbanApplication.java`

Spring Boot entry point that starts the application.

---

### Configuration

#### `resources/application.yaml`

Defines:

* Server port
* Database connection
* Hibernate configuration
* Logging and SQL formatting

---

## Testing

Unit tests are written using JUnit and Mockito.

Tests cover:

* Task creation
* Updating
* Deleting
* Filtering
* Service and controller behavior

Run tests with:

```bash
./mvnw test
```

---

## Notes

* Tasks are soft-deleted using a boolean flag.
* Filtering is handled at the repository level.
* The architecture follows best practices for scalability and maintainability.

---

## Team

Team Master – PBL Project
* Alexander
* Ivaneta
* Teodor
* Katerina
