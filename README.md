# LifePilot 🚀

**LifePilot** is a personal productivity and life-management application designed to help users organize tasks, manage priorities, and keep track of their daily activities from one place.

> 🚧 **Project Status:** In Development

The project is being developed as a full-stack application with a focus on building a clean backend architecture, RESTful APIs, database integration, authentication, validation, and eventually a user-friendly frontend.

---

## ✨ Features

### Currently Implemented

* Task management
* Create tasks
* Update tasks
* Retrieve tasks
* Delete tasks
* Request/Response DTOs
* Input validation
* MySQL database integration
* Layered Spring Boot architecture

### In Progress

* User authentication and authorization
* User-specific tasks
* Task priorities
* Task status management
* Due dates and reminders
* Improved exception handling
* API documentation
* Unit and integration testing
* Frontend integration

### Planned

* 📅 Daily planning
* 🎯 Goal management
* ⏰ Reminders
* 📊 Productivity statistics
* 🔐 JWT-based authentication
* 👤 User profiles
* 📱 Responsive frontend
* 🐳 Docker support
* 🔄 CI/CD pipeline

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot 4.0.7
* Spring Web
* Spring Data JPA
* Spring Validation
* Maven

### Database

* MySQL

### Development Tools

* IntelliJ IDEA
* Git
* GitHub
* Postman

### Planned / Future

* Spring Security
* JWT
* Docker
* Jenkins
* React
* OpenAPI / Swagger
* JUnit

---

## 🏗️ Project Architecture

LifePilot follows a layered architecture to keep responsibilities separated and make the application easier to maintain and extend.

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL Database
```

### Package Structure

```text
com.LifePilot.LifePilot
│
├── controller
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── exception
│
├── config
│
└── LifePilotApplication
```

---

## 📁 Project Structure

```text
LifePilot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/LifePilot/LifePilot/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       └── config/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── migration/
│   │
│   └── test/
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## ⚙️ Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 21
* Maven
* MySQL
* Git

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## 📥 Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/LifePilot.git
```

Move into the project:

```bash
cd LifePilot
```

---

## 🗄️ Database Setup

Create the MySQL database:

```sql
CREATE DATABASE lifepilot;
```

Configure your local database credentials using environment variables or a local configuration file.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lifepilot
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

> Never commit real database passwords, API keys, JWT secrets, or other credentials to GitHub.

---

## ▶️ Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed globally:

```bash
mvn spring-boot:run
```

The application should start on:

```text
http://localhost:8080
```

---

## 🔌 API Endpoints

### Tasks

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| GET    | `/api/tasks`      | Get all tasks  |
| GET    | `/api/tasks/{id}` | Get task by ID |
| POST   | `/api/tasks`      | Create a task  |
| PUT    | `/api/tasks/{id}` | Update a task  |
| DELETE | `/api/tasks/{id}` | Delete a task  |

> API endpoints may change while the project is under development.

---

## 🧪 Testing

Run the test suite with:

```bash
./mvnw test
```

Build the project:

```bash
./mvnw clean package
```

---

## 🔄 Development Workflow

LifePilot is actively developed and features are added incrementally.

Typical workflow:

```bash
git checkout -b feature/task-management

# Make changes

git add .
git commit -m "Add task management functionality"

git push origin feature/task-management
```

---

## 🗺️ Roadmap

* [x] Initial Spring Boot project
* [x] MySQL integration
* [x] Task entity
* [x] Task DTOs
* [x] Task repository
* [x] Task service
* [x] Task controller
* [ ] Global exception handling
* [ ] Authentication
* [ ] Authorization
* [ ] User management
* [ ] Task priorities
* [ ] Task status
* [ ] Due dates
* [ ] Reminders
* [ ] Productivity dashboard
* [ ] React frontend
* [ ] Dockerization
* [ ] Jenkins CI/CD
* [ ] Cloud deployment

---

## 🤝 Contributing

This project is currently being developed as a personal learning and portfolio project.

Suggestions and feedback are welcome.

---

## 📄 License

This project is currently available for educational and portfolio purposes.

````

---

## 3. One important improvement to your package naming

I noticed your existing package is:

```java
package com.LifePilot.LifePilot;
````

It will work, but for professional Java conventions I'd recommend lowercase package names:

```java
package com.lifepilot.lifepilot;
```

Or, even better:

```java
package com.jayanth.lifepilot;
```

Then:

```text
com.jayanth.lifepilot
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```

Java package naming convention is lowercase, so I would make this change **early**, before your project gets much bigger.

---

# 4. Before your first push

Run:

```bash
git status
```

Look carefully at the files.

You **should not** see:

```text
target/
.idea/
*.iml
.env
application-local.properties
passwords
API keys
```

Then:

```bash
git add .
git status
git commit -m "Initial LifePilot project setup"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/LifePilot.git
git push -u origin main
```

After that, your GitHub repository will already look like a real development project rather than an unfinished folder.

### One thing I'd change from your current project

Since you're actively building LifePilot, **don't make the README claim that authentication, JWT, Docker, Jenkins, React, etc. are already implemented** if they aren't. The `Currently Implemented → In Progress → Planned` separation makes the repository honest while also showing recruiters where the project is going.
