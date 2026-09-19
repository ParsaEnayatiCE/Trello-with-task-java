# Trello With Task (Java)

A simple, console-based task/board management application inspired by Trello, written in
core Java. Users can register an account, log in, create tasks, assign tasks to other
registered users, and set task deadlines — all through an interactive command-line menu
system. The project was originally built as a midterm assignment and focuses on clean
separation of concerns between menus (controllers), domain models, and input validation.

## Features

- **User registration** — sign up with full name, username, password, and birthday.
  - Duplicate usernames are rejected.
  - Passwords must match a strength pattern (digits, an uppercase letter, followed by
    lowercase letters) and are confirmed with a second entry.
  - Birthday must match a `dd/mm/yyyy` format.
- **Login** — authenticate with username and password.
  - **Password recovery** — if the password is wrong, the user can verify their identity
    with their birthday and set a new password.
- **Task management**
  - Create new tasks (each task gets an auto-incrementing ID and a title).
  - List all tasks in the system, or only the tasks assigned to the currently logged-in
    user.
  - Open a task's detail page by its ID or title.
  - Set a task's deadline (validated `dd/mm/yyyy hh:mm` format).
  - Assign a task to any registered user by username, with a confirmation step showing
    the target user's full name before assigning.
- **Menu-driven navigation** — a central `Controller` drives a simple state machine
  (`MenuEnum`) that moves the user between the welcome screen, registration, login, main
  menu, and individual task pages.
- **Input validation** — all user-provided values (passwords, birthdays, deadlines, task
  lookups) are validated with regular expressions before being accepted.

All data (users and tasks) is kept in memory for the lifetime of the running process;
there is no database or file persistence, so data resets every time the program is
restarted.

## Tech Stack

- **Java 15**
- **Maven** for dependency management and builds
- **JUnit 4 / JUnit 5** (via `junit-jupiter-engine` and `junit-platform-runner`) for unit
  tests
- Plain `java.util.Scanner` for console I/O and `java.util.regex` for input validation —
  no external frameworks or libraries beyond the test stack

## Project Structure

```
trello-with-task-java/
├── pom.xml                     # Maven descriptor (mirrors Midterm/pom.xml)
└── Midterm/                    # Maven module containing all source code
    ├── pom.xml
    └── src
        ├── main/java
        │   ├── Main.java                    # Application entry point
        │   ├── Task/
        │   │   ├── Task.java                # Task domain model (id, title, deadline, assignee)
        │   │   └── TaskPage.java             # Console page for viewing/editing a single task
        │   ├── controllers/
        │   │   ├── Controller.java           # Top-level menu loop / state machine
        │   │   ├── MainMenu.java             # Post-login menu (list/create/open tasks)
        │   │   ├── LoginMenu.java            # Login + password recovery flow
        │   │   ├── RegisterMenu.java         # User registration flow
        │   │   ├── MenuEnum.java             # States used by the Controller state machine
        │   │   └── Regex.java                # Shared validation patterns
        │   └── user/
        │       └── User.java                 # User domain model (credentials, assigned tasks)
        └── test/java
            ├── MainProgTest.java             # End-to-end console flow test
            ├── RegisterTest.java             # Registration/login flow tests
            └── TestEasyFuncs.java            # Unit tests for User/Task behaviour
```

### Notable implementation details

- **Console I/O testing**: tests drive the application by redirecting `System.in` /
  `System.out` and asserting on the exact console transcript, which exercises the menus
  end-to-end without a real terminal.
- **State machine navigation**: `Controller` holds a static `MenuEnum currentMenu` field
  that each menu screen updates to move the program to the next screen, avoiding nested
  loops or recursive menu calls.
- **In-memory model registries**: both `User` and `Task` keep static collections
  (`Map<String, User>` and `ArrayList<Task>`) that act as simple in-memory "repositories"
  for looking objects up by username, id, or title.

## Prerequisites

- JDK 15 or later
- Maven 3.6+

## Getting Started

The Maven project (both `pom.xml` and the `src` tree) lives in the `Midterm/` directory,
so run Maven commands from there.

```bash
git clone https://github.com/ParsaEnayatiCE/trello-with-task-java.git
cd trello-with-task-java/Midterm
```

### Build

```bash
mvn compile
```

### Run the tests

```bash
mvn test
```

### Run the application

The project has no `exec-maven-plugin` configured, so the simplest way to run it is to
compile and launch the `Main` class directly on the classpath:

```bash
mvn compile
java -cp target/classes Main
```

Alternatively, open the `Midterm` folder as a Maven project in an IDE (IntelliJ IDEA,
Eclipse, VS Code) and run `Main.java` directly.

## Usage

Once running, the application presents an interactive text menu:

1. At the welcome screen, type `LOGIN` to sign in, `SIGN UP` to register a new account,
   or `EXIT` to quit.
2. After registering, log in with your new username and password.
3. From the main menu you can:
   - `1` — list all tasks and open one by ID or title
   - `2` — list tasks assigned to you and open one by ID or title
   - `3` — create a new task by title
   - `4` — return to the login/register screen
4. On a task's page you can:
   - `1` — set a deadline (`dd/mm/yyyy hh:mm`)
   - `2` — assign the task to another registered user by username
   - `3` — return to the main menu

Pressing Enter on an empty prompt generally cancels the current flow and returns to the
previous menu.
