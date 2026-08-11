# Spring Design Patterns

A simple Spring Boot project where I implemented several design patterns in a small book management API.

The goal of the project was to understand how common design patterns can be used in a real application instead of only learning them theoretically.

## Design Patterns Used

### Command

Book operations such as create, update, delete and get are handled through command classes.

Examples:
- `CreateBookCommand`
- `GetAllBooksCommand`

### Observer

The project uses the Observer pattern to notify connected clients when a new book is added.

The notifications are sent through Server-Sent Events using the `/books-sse` endpoint.

### Strategy

Paragraph alignment is handled using different strategies:

- `AlignLeft`
- `AlignCenter`
- `AlignRight`

The alignment strategy can be changed at runtime.

### Composite

Books and sections are represented using a tree-like structure.

Elements such as paragraphs, images and tables can be added inside sections.

### Dependency Injection

The project also contains examples of Spring bean scopes using singleton and prototype components.

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- Gradle
- Server-Sent Events

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/books` | Get all books |
| GET | `/books/{id}` | Get one book |
| POST | `/books` | Create a book |
| PUT | `/books/{id}` | Update a book |
| DELETE | `/books/{id}` | Delete a book |
| GET | `/books-sse` | Receive book creation events |

## Run the Project

Requirements:

- Java 21

On Windows:

gradlew.bat bootRun


On Linux/macOS:

./gradlew bootRun
