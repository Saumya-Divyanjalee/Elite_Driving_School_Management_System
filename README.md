# 🚗 Elite Driving School Management System

> **Enterprise-style desktop management system for driving schools, built with Java 21, JavaFX, Hibernate ORM, and MySQL.**

The **Elite Driving School Management System** is a JavaFX-based desktop application designed to digitize and streamline driving school operations. It provides centralized management for students, instructors, courses, lessons, users, and payments through a secure, role-based system.

The application follows a **layered architecture** with clear separation between the presentation, business, and persistence layers, while using the **Factory** and **Singleton** design patterns to improve maintainability and reduce coupling.

---

## 📸 Application Preview

<!-- Add your screenshots here -->

<img width="1377" height="915" alt="Screenshot 2026-08-09 002652" src="https://github.com/user-attachments/assets/4f7b0934-861a-41e3-b212-ff2e347d8b0e" />
<img width="1707" height="992" alt="Screenshot 2026-08-09 002800" src="https://github.com/user-attachments/assets/f2874527-b8a9-402f-836f-31ed26db928b" />
<img width="1697" height="996" alt="Screenshot 2026-08-09 002914" src="https://github.com/user-attachments/assets/b979e9fd-d58e-4f9b-92e9-689b30a6a19d" />
<img width="1707" height="982" alt="Screenshot 2026-08-09 002821" src="https://github.com/user-attachments/assets/b5de5f87-6e7c-45d3-b734-b7bcf700c7a2" />
<img width="1711" height="992" alt="Screenshot 2026-08-09 002835" src="https://github.com/user-attachments/assets/c93eb9c9-a286-449f-86b1-db1d4b936c2e" />
<img width="1711" height="1003" alt="Screenshot 2026-08-09 002849" src="https://github.com/user-attachments/assets/1ab8ef84-5e6c-4818-97a9-8c608fb0a5e4" />


---

## 📌 Overview

Elite Driving School Management System replaces manual driving-school record keeping with a centralized digital solution.

The system supports two primary user roles:

### 👨‍💼 Admin

Provides full administrative access to:

* User management
* Instructor management
* Course management
* System-level operations
* Administrative dashboards

### 👩‍💼 Receptionist

Handles day-to-day front-desk operations:

* Student registration
* Student enrollment
* Lesson scheduling
* Payment processing
* Student information management

This role-based approach ensures that users only have access to the functionality required for their responsibilities.

---

# ✨ Key Features

| Module                          | Features                                                                     |
| ------------------------------- | ---------------------------------------------------------------------------- |
| 🔐 **Authentication**           | Secure login, BCrypt password hashing, role-based access                     |
| 👨‍🏫 **Instructor Management** | Add, update, delete, assign instructors to courses, manage availability      |
| 📚 **Course Management**        | Create and manage course packages, fees, duration, and instructors           |
| 🎓 **Student Management**       | Student registration, profile management, CRUD operations, course enrollment |
| 📅 **Lesson Scheduling**        | Book, reschedule, cancel, and assign lessons                                 |
| 💳 **Payment Management**       | Process payments and track payment history                                   |
| 👥 **User Management**          | Manage Admin and Receptionist accounts                                       |
| ⚡ **Caching**                   | Hibernate second-level caching using Ehcache                                 |
| 🛡️ **Exception Handling**      | Custom exception hierarchy for module-specific failures                      |
| 🔄 **Transaction Management**   | Explicit commit/rollback handling for database operations                    |
| ✅ **Validation**                | Email and phone-number validation using regular expressions                  |
| 📊 **Dashboards**               | Separate dashboards for Admin and Receptionist users                         |

---

# 🛠️ Technology Stack

| Category                 | Technology                         |
| ------------------------ | ---------------------------------- |
| **Programming Language** | Java 21                            |
| **UI Framework**         | JavaFX                             |
| **UI Design**            | FXML + CSS                         |
| **ORM**                  | Hibernate ORM                      |
| **Database**             | MySQL 8                            |
| **Caching**              | Ehcache                            |
| **Security**             | BCrypt                             |
| **Build Tool**           | Maven                              |
| **Architecture**         | Layered Architecture               |
| **Design Patterns**      | Factory, Singleton                 |
| **Module System**        | Java Platform Module System (JPMS) |
| **Version Control**      | Git + GitHub                       |

---

# 🏗️ Architecture

The application follows a **layered architecture** to maintain a clean separation of responsibilities.

```text
┌─────────────────────────────┐
│        JavaFX UI            │
│ Controllers / FXML / CSS    │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│       Business Layer        │
│       BO / BOImpl           │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│      Persistence Layer      │
│       DAO / DAOImpl         │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│       Hibernate ORM         │
│         Entities            │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│          MySQL              │
└─────────────────────────────┘
```

### Design Patterns

#### 🏭 Factory Pattern

The application uses `BOFactory` and `DAOFactory` to decouple controllers and business logic from concrete implementations.

```text
Controller
    ↓
BOFactory
    ↓
BO Interface
    ↓
BO Implementation
```

```text
BO
    ↓
DAOFactory
    ↓
DAO Interface
    ↓
DAO Implementation
```

This improves **loose coupling, maintainability, and extensibility**.

#### 🔒 Singleton Pattern

`FactoryConfiguration` provides a single shared Hibernate `SessionFactory` instance throughout the application.

This prevents unnecessary creation of multiple `SessionFactory` instances and manages Hibernate resources efficiently.

#### 📦 DTO / TM Separation

The project separates:

* **DTOs** → data transfer, validation, and business communication
* **Entities** → Hibernate persistence and database mapping
* **TM classes** → JavaFX `TableView` presentation models

This keeps the UI, business logic, and persistence responsibilities independent.

---

# 📁 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── lk/ijse/orm/elite_driving_school_management_system/
    │       │
    │       ├── App.java
    │       ├── AppInitializer.java
    │       ├── module-info.java
    │       │
    │       ├── bo/
    │       │   ├── BOFactory.java
    │       │   ├── SuperBO.java
    │       │   └── custom/
    │       │       ├── CourseBO.java
    │       │       ├── InstructorBO.java
    │       │       ├── LessonBO.java
    │       │       ├── PaymentBO.java
    │       │       ├── QueryBO.java
    │       │       ├── StudentBO.java
    │       │       └── UserBO.java
    │       │
    │       ├── dao/
    │       │   ├── DAOFactory.java
    │       │   ├── SuperDAO.java
    │       │   └── custom/
    │       │       ├── CrudDAO.java
    │       │       ├── CourseDAO.java
    │       │       ├── InstructorDAO.java
    │       │       ├── LessonDAO.java
    │       │       ├── PaymentDAO.java
    │       │       ├── QueryDAO.java
    │       │       ├── StudentDAO.java
    │       │       └── UserDAO.java
    │       │
    │       ├── config/
    │       │   └── FactoryConfiguration.java
    │       │
    │       ├── controller/
    │       │   ├── LoginController.java
    │       │   ├── LogoutController.java
    │       │   ├── LoadingScreenController.java
    │       │   ├── UserRoleController.java
    │       │   ├── AdminDashboard.java
    │       │   ├── ReceptionistDashboard.java
    │       │   ├── StudentController.java
    │       │   ├── InstructorController.java
    │       │   ├── CourseController.java
    │       │   ├── LessonSchedulingController.java
    │       │   └── PaymentController.java
    │       │
    │       ├── dto/
    │       │   ├── UserDTO.java
    │       │   ├── StudentDTO.java
    │       │   ├── InstructorDTO.java
    │       │   ├── CourseDTO.java
    │       │   ├── LessonDTO.java
    │       │   └── PaymentDTO.java
    │       │
    │       ├── entity/
    │       │   ├── SuperEntity.java
    │       │   ├── User.java
    │       │   ├── Student.java
    │       │   ├── Instructor.java
    │       │   ├── Course.java
    │       │   ├── Lesson.java
    │       │   └── Payment.java
    │       │
    │       ├── exception/
    │       │   ├── LoginException.java
    │       │   ├── RegistrationException.java
    │       │   ├── PaymentException.java
    │       │   └── SchedulingException.java
    │       │
    │       └── tm/
    │           ├── UserTM.java
    │           ├── StudentTM.java
    │           ├── InstructorTM.java
    │           ├── CourseTM.java
    │           ├── LessonTM.java
    │           └── PaymentTM.java
    │
    └── resources/
        ├── assets/
        │   └── logo.png
        │
        ├── lk/ijse/orm/
        │   └── elite_driving_school_management_system/
        │       ├── DataBase.sql
        │       ├── migration_payment_table.sql
        │       ├── hibernate.properties
        │       └── ehcache.xml
        │
        ├── style/
        │   ├── style.css
        │   └── Admin-dashboard.css
        │
        └── view/
            ├── Login.fxml
            ├── LoadingScreen.fxml
            ├── User-Role.fxml
            ├── Admin-dashboard.fxml
            ├── Receptionist-dashboard.fxml
            ├── Student.fxml
            ├── Instructor.fxml
            ├── Course.fxml
            ├── Lesson.fxml
            └── Payment.fxml
```

---

# 🗄️ Database Design

The system uses **MySQL** with Hibernate ORM for persistence.

### Main Tables

| Table             | Description                                  |
| ----------------- | -------------------------------------------- |
| `users`           | Authentication credentials and user roles    |
| `students`        | Student registration and profile information |
| `instructor_name` | Instructor information and availability      |
| `course_table`    | Course packages, duration, and fees          |
| `lesson_table`    | Scheduled driving lessons                    |
| `payment_table`   | Student payment records                      |
| `student_course`  | Student ↔ Course many-to-many relationship   |

### Entity Relationships

| Relationship        | Cardinality  |
| ------------------- | ------------ |
| Instructor → Lesson | One-to-Many  |
| Course → Lesson     | One-to-Many  |
| Student → Lesson    | One-to-Many  |
| Student → Payment   | One-to-Many  |
| Student ↔ Course    | Many-to-Many |
| Instructor ↔ Course | Many-to-Many |

Database schema and initial data are provided through:

```text
DataBase.sql
migration_payment_table.sql
```

---

# 🔐 Security & Reliability

Security and database consistency are important aspects of the system.

### 🔑 Password Security

User passwords are protected using **BCrypt hashing**.

```text
Plain Password
      ↓
   BCrypt
      ↓
Hashed Password
      ↓
    MySQL
```

During authentication, password verification is performed using BCrypt rather than comparing or storing plain-text passwords.

### 👥 Role-Based Access Control

The system separates functionality based on user roles:

```text
                    Login
                      │
             ┌────────┴────────┐
             ▼                 ▼
           ADMIN          RECEPTIONIST
             │                 │
             ▼                 ▼
      Full Management     Front Desk
```

### 🔄 Transaction Management

DAO write operations use explicit transaction management:

```text
Begin Transaction
       ↓
   Database Operation
       ↓
   ┌─── Success ───┐
   │                │
 Commit           Rollback
   │                │
   ▼                ▼
Success           Failure
       ↓
Session Cleanup
```

Transactions are rolled back when an operation fails, helping maintain data consistency and avoid unfinished database transactions.

### ⚠️ Custom Exceptions

The application defines module-specific exceptions:

* `LoginException`
* `RegistrationException`
* `PaymentException`
* `SchedulingException`

This allows the application to provide more meaningful error handling and user feedback.

---

# ⚡ Performance

The application uses **Hibernate second-level caching with Ehcache** to reduce unnecessary database access.

Caching can improve performance when frequently accessed data is requested repeatedly.

```text
Application
     ↓
Hibernate
     ↓
Ehcache
  ↙     ↘
Hit     Miss
 ↓        ↓
Data    MySQL
```

---

# 🖥️ Application Modules

### 🔐 Authentication

* Login
* User role selection
* BCrypt password verification
* Role-based dashboard routing

### 👨‍🏫 Instructor Management

* Register instructors
* Update instructor details
* Delete instructors
* Assign instructors to courses
* Manage instructor availability

### 📚 Course Management

* Create courses
* Update courses
* Delete courses
* Manage course fees and duration
* Assign instructors

### 🎓 Student Management

* Register students
* Update student profiles
* Delete student records
* Enroll students in multiple courses

### 📅 Lesson Scheduling

* Schedule lessons
* Assign students
* Assign instructors
* Associate courses
* Reschedule lessons
* Cancel lessons

### 💳 Payment Management

* Process payments
* Track payment history
* Track pending/completed transactions
* Associate payments with students and courses

---

# 🚀 Getting Started

## Prerequisites

Make sure you have the following installed:

* **JDK 21+**
* **MySQL 8.x**
* **Maven**
* Git

---

## 1. Clone the Repository

```bash
git clone https://github.com/Saumya-Divyanjalee/Elite_Driving_School_Management_System.git

cd Elite_Driving_School_Management_System
```

---

## 2. Create the Database

Create the database:

```sql
CREATE DATABASE elite_driving_school;
```

Then import the provided SQL script:

```bash
mysql -u root -p elite_driving_school < src/main/resources/lk/ijse/orm/elite_driving_school_management_system/DataBase.sql
```

---

## 3. Configure Hibernate

Open:

```text
src/main/resources/lk/ijse/orm/elite_driving_school_management_system/hibernate.properties
```

Update the database credentials:

```properties
hibernate.connection.url=jdbc:mysql://localhost:3306/elite_driving_school?createDatabaseIfNotExist=true
hibernate.connection.username=your_username
hibernate.connection.password=your_password

hibernate.hbm2ddl.auto=update
```

> ⚠️ Do not commit real database passwords or other sensitive credentials to GitHub.

---

## 4. Run the Application

Using Maven:

```bash
./mvnw clean javafx:run
```

On Windows:

```bash
mvnw.cmd clean javafx:run
```

---

# 🗺️ Roadmap

### Completed

* [x] User authentication
* [x] BCrypt password hashing
* [x] Role-based access control
* [x] Admin dashboard
* [x] Receptionist dashboard
* [x] Student management
* [x] Instructor management
* [x] Course management
* [x] Lesson management
* [x] Payment management
* [x] Core CRUD operations
* [x] Factory pattern implementation
* [x] Singleton configuration
* [x] Hibernate ORM integration
* [x] Ehcache second-level caching
* [x] Transaction rollback handling
* [x] Custom exception handling
* [x] Input validation

### Planned

* [ ] Lesson scheduling conflict detection
* [ ] Instructor self-service portal
* [ ] Instructor schedule view
* [ ] Reporting and analytics dashboard
* [ ] Payment reports
* [ ] Email lesson reminders
* [ ] Automated notification system
* [ ] Advanced dashboard statistics

---

# 📚 Key Concepts Demonstrated

This project demonstrates practical implementation of:

* Object-Oriented Programming
* Java 21
* JavaFX
* FXML
* CSS
* Hibernate ORM
* JPA concepts
* MySQL
* JDBC
* Layered Architecture
* Factory Design Pattern
* Singleton Design Pattern
* Data Transfer Objects (DTO)
* DAO Pattern
* Business Object Pattern
* Transaction Management
* Database Relationships
* Many-to-Many Mapping
* Exception Handling
* BCrypt Authentication
* Role-Based Access Control
* Second-Level Caching
* Maven
* JPMS
* Git & GitHub

---

# 🎯 Project Goals

The main goals of this project are to:

1. Digitize driving school management processes.
2. Reduce manual record keeping.
3. Provide secure role-based access.
4. Maintain clean separation of application layers.
5. Demonstrate practical enterprise application development using Java.
6. Provide a maintainable foundation for future features.

---

# 👤 Author

### Saumya Divyanjalee

Software Engineering Student & Developer

* GitHub: [@Saumya-Divyanjalee](https://github.com/Saumya-Divyanjalee)

---

# 📄 License

This project was developed for **academic and educational purposes** as part of the **Institute of Software Engineering (IJSE) Software Engineering program**.

---

## ⭐ Support

If you find this project useful or interesting, consider giving it a ⭐ on GitHub.
