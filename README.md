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

7. **Error Handling and Logging**
   - Robust error management and system logging for smooth operation.

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

![photo_2024-12-18_12-13-27](https://github.com/user-attachments/assets/2a00e21c-e75d-4029-b2ad-195da725eb25)
![photo_2024-12-18_12-13-31](https://github.com/user-attachments/assets/380d7fbe-8615-4ac8-a610-8144b9ba7084)
![photo_2024-12-18_12-13-34](https://github.com/user-attachments/assets/27aaafb2-492f-4279-ab27-4a2154e35d83)
![photo_2024-12-18_12-13-49](https://github.com/user-attachments/assets/358006b9-79ee-4cf4-8884-a8aeff20f54b)
![photo_2024-12-18_12-13-51](https://github.com/user-attachments/assets/de841241-1466-4832-9a11-9d3d7677d888)
![photo_2024-12-18_12-13-52](https://github.com/user-attachments/assets/a60b7722-9649-44d1-8a62-688b0ad15660)
![photo_2024-12-18_12-13-54](https://github.com/user-attachments/assets/59c3996e-c57b-4f80-a2b2-09d6914c1a9b)



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

## UML Class diagram
Link for google drive with the file : https://drive.google.com/drive/folders/1hkCivXFjPm7oOmqbbKJObka5NJy2cBQg?usp=sharing
Link for the website to open file : https://app.diagrams.net/?src=about

# 1. Getting Started
## 1.1 Download the Code
Clone the repository to your local machine using Git:
- bash
- git clone https://github.com/your-repository-url.git
### Navigate to the project directory:
- bash
- cd your-project-directory
## 1.2 Prerequisites
Make sure the following software is installed on your machine:

- Java 11+ (Required for JavaFX)
- PostgreSQL (for database management)
- Maven (if you are not using an IDE that handles dependencies automatically, like IntelliJ IDEA)
- JavaFX SDK (ensure the JavaFX libraries are properly set up if using Java 11 or later)
# 2. Database Setup
## 2.1 Create the Database
1. Open PostgreSQL
- CREATE DATABASE Tms;
2. Switch to the new database:
- \c Tms
## 2.2 Create tables
Download and run the sql-script that create tables
[Download SQL Script](src/sql-scripts/sql table creation.txt)
