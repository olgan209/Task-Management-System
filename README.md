# Task Management System

## Description
The **Task Management System** is designed to help users efficiently manage their tasks and projects. This application allows users to create, read, update, and delete tasks and projects while providing an intuitive interface and smooth functionality for task organization and tracking progress.

---

## Features

1. **User Registration and Authentication System**
   - Secure login and account management for users.

2. **Task Management (CRUD)**
   - Users can create, read, update, and delete tasks to stay organized.

3. **Project Management (CRUD)**
   - Allows seamless project creation, updates, and tracking.

4. **Database Integration**
   - Persistent storage of tasks, projects, and user information using a relational database.

5. **Unit Tests**
   - Comprehensive testing to ensure system reliability and correctness.

6. **Validation Utilities**
   - Data integrity ensured through validation mechanisms.

7. **Poka**
   -NE PRIDUMALA.

8. **Documentation**
   - Includes a detailed `ReadMe.md` file for easy setup and usage.

9. **UML Diagram**
   - A UML diagram to visually represent the application's architecture.

10. **Project Presentation**
    - A comprehensive presentation for demonstrating the application.

---

## Team Members

# **Team Members and Responsibilities**

| **Name**              | **Role and Responsibilities**                                                                                                                                                                                                 |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Neboga Olga**       | - Developed all classes in the **model package**, including `User`, `Project`, `Task`, and `TaskStatus`, ensuring proper attribute definitions and relationships between entities.                                           |
|                       | - Implemented core business logic in the services package, including:                                                                                                                  |
|                       |   - `UserService`: Handles user registration, authentication, and account management.                                                                                                  |
|                       |   - `ProjectService`: Manages CRUD operations for projects, ensuring database synchronization and integrity.                                                                            |
|                       |   - `TaskService`: Implements CRUD operations for tasks, including assigning tasks to projects and managing task statuses.                                                             |
|                       |   - `ValidationUtils`: Validates user input, ensuring data accuracy and preventing invalid entries into the database.                                                                   |
|                       | - Authored the `ReadMe.md` file with detailed project setup instructions, feature descriptions, and usage guidelines.                                                                   |
|                       | - Created a UML diagram representing the project’s architecture, including class structures and their relationships.                                                                   |
|                       | - Prepared the final project presentation, including slides and a demonstration of the system's features and functionality.                                                             |
| **Talantbekov Nazar** | - Developed all classes in the **view package**, ensuring a user-friendly and responsive UI design for smooth navigation and task/project management.                                      |
|                       | - Built the `DatabaseConnection` class in the **services package**, establishing a reliable connection between the application and the database.                                         |
|                       | - Implemented controllers for application navigation and functionality:                                                                                                                 |
|                       |   - `LoginController`: Manages the login and authentication process.                                                                                                                    |
|                       |   - `MainController`: Serves as the central controller for handling navigation between tabs (Tasks, Projects, User Profile, etc.).                                                       |
|                       |   - `ProjectReviewController`: Handles the display and editing of project details.                                                                                                      |
|                       |   - `TaskReviewController`: Manages task visualization, updates, and deletion workflows.                                                                                                |
|                       | - Developed classes in the **model package** for data representation:                                                                                                                   |
|                       |   - `Project`: Represents project entities with attributes such as name, description, and associated tasks.                                                                             |
|                       |   - `Task`: Represents task entities with attributes such as title, description, status, and project association.                                                                       |
|                       |   - `TaskStatus`: Enum class to manage task states (e.g., To Do, In Progress, Done).                                                                                                     |
| **Tabaldiev Aslan**   | - Implemented all classes in the **controllers package** that are not directly tied to navigation, including logic for handling user actions, form submissions, and error messages.                                           |
|                       | - Designed and set up the relational **database**, ensuring proper table structures and relationships:                                                                                  |
|                       |   - Created tables for `users`, `tasks`, `projects`, and `task statuses`, aligning them with the model classes.                                                                         |
|                       |   - Configured primary and foreign keys to establish clear relationships between tasks, projects, and users.                                                                             |
|                       | - Wrote **unit tests** to validate core functionalities, including:                                                                                                                     |
|                       |   - User authentication and registration workflows.                                                                                                                                    |
|                       |   - CRUD operations for tasks and projects.                                                                                                                                           |
|                       |   - Validation utilities to ensure proper handling of incorrect or incomplete data input.                                                                                              |


---

## Application screenshots


## Project Timeline

### 6.12 (First Meeting)
- Task allocation and responsibility discussion.
- Preliminary project architecture created by Olga.

### 15.12 (Second Meeting)
- Addressed challenges in:
  - Tab navigation and button logic.
  - Image upload functionality in the database.
  - Attribute alignment between model classes and the database schema.
- Resolved the above issues successfully.
- Planned next steps:
  - Finalize the `ReadMe.md` file.
  - Create the UML diagram.
  - Prepare the project presentation.

---

## How to Contribute

1. Clone the repository:
   ```bash
   git clone <repository_url>
