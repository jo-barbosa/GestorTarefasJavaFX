# JavaFX Task Manager

A simple desktop app built with Java 21 and JavaFX to manage personal tasks, with user registration/login and file persistence.

---

## Features

- Register and authenticate users
- Add tasks with:
  - Description
  - Priority (LOW, MEDIUM, HIGH — default MEDIUM)
  - Due date
- List, remove, and mark tasks as completed (stores completion date)
- Persistence using Java serialization to files under data/
- Basic navigation: Login → Task Manager → Exit back to Login

---

## Tech stack

- Java 21
- JavaFX 21 (controls, fxml)
- IntelliJ IDEA
- Java serialization for persistence

---

## How to run

### Requirements
- JDK 21
- JavaFX SDK 21 installed locally (e.g., C:\\libs\\javafx-sdk-21.0.8)
- IntelliJ IDEA

### Setup in IntelliJ
1. Add JavaFX libraries:
   - File → Project Structure → Libraries → + → Java
   - Select the lib/ folder inside your JavaFX SDK (e.g., C:\\libs\\javafx-sdk-21.0.8\\lib)
2. Configure Run/Debug configuration (VM options):
   - Windows example:
     --module-path "C:\\libs\\javafx-sdk-21.0.8\\lib" --add-modules javafx.controls,javafx.fxml
   - macOS/Linux example:
     --module-path /path/to/javafx-sdk-21.0.8/lib --add-modules javafx.controls,javafx.fxml
3. Run MainApp

---

## Project structure

```
src/
└── com/jobarbosa/gestortarefas/
    ├── MainApp.java
    ├── LoginController.java
    ├── RegistrationController.java
    ├── TaskManagerController.java
    ├── TasksManagementController.java
    ├── UserManagementController.java
    ├── Task.java
    ├── Tasks.java
    ├── User.java
    ├── Users.java
    ├── login.fxml
    ├── registration.fxml
    └── task_manager.fxml

data/
├── users.dat
└── tasks.dat
```

Notes:
- UI is currently created in Java code; FXML files are present and can be wired later.
- Data files are created automatically if they don’t exist.

---

## Persistence

- Users and tasks are serialized to:
  - data/users.dat
  - data/tasks.dat
- Data is loaded on app startup and saved on changes (e.g., add/remove/complete).

---

## Troubleshooting

- Error: Cannot resolve symbol 'javafx' / package javafx.* does not exist
  - Ensure JavaFX SDK is added as a Library in IntelliJ.
  - Ensure VM options include the correct --module-path and --add-modules values (see above).
  - Confirm the JavaFX SDK path matches your installation.

---

## Next steps

- Wire FXML views to controllers
- Add task filters (by priority, due date, completed)
- Improve validation and error feedback

---

## Author

Developed by Jorge Barbosa.
