# spring-design-patterns

Spring Boot REST API built around a books management domain, used as a playground for implementing classic design patterns. Each feature of the app demonstrates one or more patterns in a practical context rather than just as isolated examples.

Built as part of the Software Patterns Lab at Facultatea de Informatica, UVT Timisoara.

---

## Patterns implemented

**Command** — each CRUD operation is wrapped in a command object (`CreateBookCommand`, `GetAllBooksCommand`, etc.) implementing a generic `Command<R>` interface. The controller constructs and executes commands rather than calling the service directly.

**Observer + Server-Sent Events** — `AllBooksSubject` maintains a list of `BooksObserver` instances. When a new book is created, all attached observers are notified. `BooksSseController` wires this to an SSE endpoint (`/books-sse`) so clients receive real-time push updates without polling.

**Strategy** — text alignment in the book rendering layer is handled via `AlignStrategy` with three implementations: `AlignLeft`, `AlignCenter`, `AlignRight`. The strategy is injected into `Paragraph` at runtime.

**Composite** — the book document model uses a composite tree: `Element` (interface) → `BaseElement` → `Section` and `Book`, with `Paragraph`, `Image`, and `Table` as leaf nodes. A `Section` can contain any `Element`, and `Book` extends `Section`.

**DI scopes** — `SingletonComponent` and `TransientComponent` demonstrate the difference between Spring's singleton and prototype bean scopes, injected into `ClientComponent` via constructor injection.

---

## Stack

- Java 21, Spring Boot 3.3.5
- Spring Data JPA + H2 (file-based, persists between restarts)
- Lombok
- Gradle

---

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/books` | Get all books |
| GET | `/books/{id}` | Get book by ID |
| POST | `/books` | Create a book |
| PUT | `/books/{id}` | Update a book |
| DELETE | `/books/{id}` | Delete a book |
| GET | `/books-sse` | SSE stream — pushed on every new book |

H2 console available at `http://localhost:8080/h2-console` (datasource: `jdbc:h2:file:~/h2db/booksdb`).

---

## Running locally

Requirements: Java 21, Gradle (or use the wrapper)

```bash
./gradlew bootRun
```

The app starts on `http://localhost:8080`.
