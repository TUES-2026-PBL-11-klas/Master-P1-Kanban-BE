# Master-P1-Kanban-BE
The backend repo for the first project of team Master for the second term PBL

# Main
This is where the core logic is.
## Java/Master/Kanban

### contoller/APIController.java
This file exposes HTTP endpoints under /api/v1/tasks, also recieves HTTP requests like GET, POST, PUT and DELETE. It's responsible for converting the java objects to json, so that the frontend can interact with them. The controller is also responsible for delegating work to the KanbanService.

### Model

#### State.java 
This class represents the possible states of the tasks. The states are: Newly created, Work in progress and Finished.

#### Taks.java
The core class of the whole program. It represents an individual task which contains: id, user, deleted, title, description, priority and date of creation. 

#### User.java
Each user contains id and username and a list of the tasks the user has.

### Repository/KanbanRepository.java
Repository interface to create the task enteties. Provides a set of CRUD operations.

### Service

#### KanbanService.java
Service interface for managing task entities. Defines how the operations on task such as retrieving, creating, updating, deleting, and filtering. It has different methods for filtering the tasks, such as: getting all tasks of a specific user, getting a task by its index, updating a task, deleting a task, creating a task and filtering tasks by state. 

#### KanbanServiceImplementation.java
Creates the logic behind how the functions of KanbanService.java will work.

### KanbanApplication.java
This file combines all other and runs the app.

### Resources/application.yaml
If there is no port it goes to 8085 by default. Setes the application name to Kanban, connects to the database and configures Hibernate to automatically manage the schema and show formatted queries. 

### test/java/Master/Kanban/KanbanApplicationTests.java
Creates unit tests for the application. Tests for creating a task, updating a task and deleting a task, as well as testing the different filters for tasks.

### Mvnw
Allows running Maven without downloading it manually. 

### Pom.xml
Sets all dependencies for the project and how it is built and the metadata for it.
