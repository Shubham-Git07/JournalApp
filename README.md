# 📝 Journal Management Application

A Spring Boot web application to create, update, view, and delete personal journal entries.  
Built with REST APIs, Spring Data JPA, and MySQL.  
Currently enhancing the project by adding Spring Security for authentication and role-based access control.

---

## 🚀 Features
- Create, read, update, and delete (CRUD) journal entries
- RESTful API endpoints for all operations
- Automatic timestamps using `LocalDateTime` and JPA lifecycle callbacks
- Responsive UI with Thymeleaf + Bootstrap
- Hot reload during development using Spring Boot DevTools
- **Upcoming:** Spring Security for authentication & authorization

---

## 🛠 Tech Stack
- **Backend:** Spring Boot, Spring Data JPA
- **Frontend:** Thymeleaf, Bootstrap
- **Database:** MySQL
- **Tools:** IntelliJ IDEA, Maven
- **Languages:** Java, HTML, CSS

---

## 📡 API Endpoints
| Method | Endpoint           | Description               |
|--------|--------------------|---------------------------|
| GET    | `/api/journals`    | Get all journal entries    |
| GET    | `/api/journals/{id}` | Get journal by ID         |
| POST   | `/api/journals`    | Create a new journal entry |
| PUT    | `/api/journals/{id}` | Update an existing entry  |
| DELETE | `/api/journals/{id}` | Delete an entry           |

---

🔐 Upcoming Features
User registration & login

Role-based access control

Password encryption with BCrypt

Session management
