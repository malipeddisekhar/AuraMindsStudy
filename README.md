# 🚀 Augmind - Intelligent Study & Task Management Platform

> *A powerful, production-ready Spring Boot application for managing tasks, schedules, notes, and study progress with elegant UI and robust backend*

---

## ✨ About Augmind

**Augmind** is a comprehensive study and task management application designed for students, professionals, and organizations who want to streamline their productivity. It combines intelligent task tracking, schedule planning, subject management, note-taking, and personal metrics to help users stay focused and organized.

Whether you're managing academic workload, personal projects, or team tasks, Augmind provides an intuitive interface backed by a powerful Java Spring Boot backend with enterprise-grade database support.

### 🌐 **Live Deployment**
**Visit the live application here:** ➡️ **[https://augmind-app.onrender.com/](https://augmind-app.onrender.com/)**

> Ready to use! No setup required. Just visit the link and start managing your tasks!

---

## 🎯 Key Features

### 📋 Task Management
- Create, update, and delete tasks with priority levels
- Track task status: Active, Completed, or All
- Real-time task filtering and sorting
- Priority-based organization (High, Medium, Low)

### 📚 Subject Management
- Organize your study materials by subject
- Track subjects with detailed history
- Quick subject access and management
- Subject-specific progress tracking

### 📅 Schedule Planning
- Create and manage your daily schedule
- Plan study sessions with time allocation
- View schedule history by date
- Flexible schedule adjustment

### 📝 Advanced Notes System
- Create rich notes with detailed content
- Note organization and history tracking
- Quick access to recent notes
- Structured note taking framework

### 📊 Personal Metrics & Analytics
- Track your productivity metrics
- Monitor study progress
- View statistical insights
- Performance analytics dashboard

### 🔐 Security & Access Control
- **ACCESS CODE PROTECTION** - Secured access with customizable access codes
- Role-based access management
- Session-based authentication
- Protected API endpoints

### 🎨 Beautiful UI
- Responsive modern interface
- Dark mode support
- Mobile-friendly design
- Fast and intuitive navigation

---

## 💻 Technology Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| **Language** | Java | 21 (LTS) |
| **Framework** | Spring Boot | 3.4.4 |
| **Web** | Spring Web MVC | 3.4.4 |
| **Data** | Spring Data JPA | 3.4.4 |
| **Validation** | Spring Validation | 3.4.4 |
| **Templates** | Thymeleaf | - |
| **Database** | MySQL 8+ / H2 (fallback) | 8.0+ |
| **Build Tool** | Maven | 4.0.0 |
| **Containerization** | Docker + Docker Compose | Latest |
| **Deployment** | Render | - |

---

## 📁 Project Structure

```
augmind-app/
├── src/
│   ├── main/
│   │   ├── java/com/augmind/app/
│   │   │   ├── AugmindApplication.java          # Main Spring Boot Entry Point
│   │   │   ├── config/                          # Configuration Classes
│   │   │   │   ├── AccessGateInterceptor.java   # Access Control Logic
│   │   │   │   ├── ApiExceptionHandler.java     # Global Exception Handling
│   │   │   │   ├── AppProperties.java           # Application Properties
│   │   │   │   ├── DataInitializer.java         # Initial Data Setup
│   │   │   │   ├── DataSourceConfig.java        # Database Configuration
│   │   │   │   ├── TomcatConfig.java            # Servlet Configuration
│   │   │   │   └── WebConfig.java               # Web Configuration
│   │   │   ├── domain/                          # Entity Classes
│   │   │   │   ├── TaskItem.java                # Task Domain Model
│   │   │   │   ├── SubjectItem.java             # Subject Domain Model
│   │   │   │   ├── ScheduleItem.java            # Schedule Domain Model
│   │   │   │   ├── NoteItem.java                # Note Domain Model
│   │   │   │   ├── UserMetrics.java             # Metrics Domain Model
│   │   │   │   └── Priority.java                # Priority Enumeration
│   │   │   ├── dto/                             # Data Transfer Objects
│   │   │   │   ├── TaskRequest/Response         # Task DTOs
│   │   │   │   ├── ScheduleRequest/Response     # Schedule DTOs
│   │   │   │   ├── SubjectRequest/Response      # Subject DTOs
│   │   │   │   ├── NoteRequest/Response         # Note DTOs
│   │   │   │   ├── StatsResponse.java           # Statistics DTO
│   │   │   │   └── ApiError.java                # Error Response DTO
│   │   │   ├── repository/                      # JPA Repositories
│   │   │   │   ├── TaskRepository              # Task Data Access
│   │   │   │   ├── SubjectRepository           # Subject Data Access
│   │   │   │   ├── ScheduleRepository          # Schedule Data Access
│   │   │   │   ├── NoteRepository              # Note Data Access
│   │   │   │   └── UserMetricsRepository       # Metrics Data Access
│   │   │   ├── service/                         # Business Logic Layer
│   │   │   │   ├── TaskService                 # Task Service Logic
│   │   │   │   ├── SubjectService              # Subject Service Logic
│   │   │   │   ├── ScheduleService             # Schedule Service Logic
│   │   │   │   ├── NoteService                 # Note Service Logic
│   │   │   │   └── MetricsService              # Metrics Service Logic
│   │   │   └── web/                             # REST Controllers
│   │   │       ├── TaskController              # Task REST Endpoints
│   │   │       ├── SubjectController           # Subject REST Endpoints
│   │   │       ├── ScheduleController          # Schedule REST Endpoints
│   │   │       ├── NoteController              # Note REST Endpoints
│   │   │       ├── MetricsController           # Metrics REST Endpoints
│   │   │       ├── AccessController            # Access Control Endpoints
│   │   │       └── ApiSmokeTest                # API Integration Tests
│   │   └── resources/
│   │       ├── application.properties           # Spring Boot Configuration
│   │       ├── static/
│   │       │   ├── index.html                  # Main Frontend
│   │       │   ├── access.html                 # Access Code Page
│   │       │   └── denied.html                 # Access Denied Page
│   │       └── templates/
│   │           └── index.html                  # Thymeleaf Template
│   └── test/
│       └── java/com/augmind/app/web/
│           └── ApiSmokeTest.java               # Integration Tests
├── pom.xml                                     # Maven Configuration
├── Dockerfile                                  # Docker Image Definition
├── docker-compose.yml                          # Container Orchestration
├── render.yaml                                 # Render Deployment Config
└── README.md                                   # This File

```

### Architecture Overview

**Layered Architecture Pattern:**
- **Web Layer** → REST Controllers handling HTTP requests
- **Service Layer** → Business logic and data processing
- **Repository Layer** → Data persistence with JPA
- **Domain Layer** → Entity models representing business data
- **Configuration Layer** → Application setup and middleware

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 21 or higher
- **Maven** 3.8.1 or higher
- **MySQL** 8.0 or higher (or Docker)
- **Git** (optional, for cloning)
- **VS Code** or **IntelliJ IDEA** (recommended IDE)

### Local Development Setup

#### 1️⃣ **Clone/Download the Project**
```bash
# Clone from repository
git clone <repository-url>
cd augmind-app

# Or download as ZIP and extract
```

#### 2️⃣ **Install and Configure MySQL**

**Option A: Local MySQL Installation**
```bash
# Create database
mysql -u root -p

# In MySQL shell
CREATE DATABASE augmind;
USE augmind;
```

**Option B: Docker MySQL (Recommended)**
```bash
# Run MySQL in Docker
docker run -d \
  --name mysql-augmind \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=augmind \
  -p 3306:3306 \
  mysql:8.0
```

#### 3️⃣ **Configure Environment Variables**

Create a `.env` file in the project root:
```env
DB_URL=jdbc:mysql://localhost:3306/augmind?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USER=root
DB_PASSWORD=root
PORT=8080
ACCESS_CODE_HASH=your_custom_hash
```

Or set system environment variables:
```bash
# Windows PowerShell
$env:DB_URL="jdbc:mysql://localhost:3306/augmind..."
$env:DB_USER="root"
$env:DB_PASSWORD="root"

# Linux/Mac
export DB_URL="jdbc:mysql://localhost:3306/augmind..."
export DB_USER="root"
export DB_PASSWORD="root"
```

#### 4️⃣ **Install Dependencies**
```bash
mvn clean install
```

#### 5️⃣ **Run the Application**

**Using Maven:**
```bash
mvn spring-boot:run
```

**Using Java directly:**
```bash
java -jar target/augmind-app-1.0.0.jar
```

**Using IDE:**
- Right-click `AugmindApplication.java` → Run or Debug

#### 6️⃣ **Access the Application**
```
🌐 Local Frontend: http://localhost:8080
🌐 Live Deployment: https://augmind-app.onrender.com/
📝 Access Code Required: Enter your access code
```

---

## 🐳 Running with Docker

### Docker Compose (Recommended)

```bash
# Build and start all services
docker compose up --build

# Run in background
docker compose up -d --build

# Stop services
docker compose down

# View logs
docker compose logs -f augmind-app
```

**Services included:**
- `augmind-app` - Spring Boot Application (Port 8080)
- `mysql-db` - MySQL Database (Port 3306)

### Standalone Docker

```bash
# Build Docker image
docker build -t augmind-app:1.0.0 .

# Run container
docker run -d \
  --name augmind \
  -p 8080:8080 \
  -e DB_URL=jdbc:mysql://mysql-host:3306/augmind \
  -e DB_USER=root \
  -e DB_PASSWORD=root \
  augmind-app:1.0.0
```

---

## 🌐 API Documentation

### REST Endpoints

#### **Task Management**
```
GET    /tasks                    # Get all tasks
GET    /tasks?status=active      # Get active tasks
POST   /tasks                    # Create new task
PATCH  /tasks/{id}/toggle        # Toggle task completion
DELETE /tasks/{id}               # Delete task
```

#### **Subject Management**
```
GET    /subjects                 # Get all subjects
POST   /subjects                 # Create new subject
DELETE /subjects/{id}            # Delete subject
```

#### **Schedule Management**
```
GET    /schedules                # Get all schedules
POST   /schedules                # Create new schedule
DELETE /schedules/{id}           # Delete schedule
```

#### **Notes Management**
```
GET    /notes                    # Get all notes
POST   /notes                    # Create new note
DELETE /notes/{id}               # Delete note
```

#### **Metrics & Analytics**
```
GET    /metrics                  # Get user metrics
GET    /stats                    # Get statistics
```

#### **Access Control**
```
GET    /access                   # Access code page
POST   /verify-access            # Verify access code
```

---

## 🔐 Security & Access Control

### ⛔ **ACCESS DENIED PAGE**

The application includes a **comprehensive access control system** that protects sensitive features:

```
🚫 DENIED ACCESS HANDLING
   ├── Unverified users are redirected to /access page
   ├── Incorrect access code shows /denied page
   ├── Session-based authentication maintained
   ├── Custom interceptors validate each request
   └── Fallback to H2 database if DB credentials missing
```

**ACCESS FLOW:**
1. User visits application
2. `AccessGateInterceptor` checks session validity
3. If no valid session → Redirect to `/access` (Access Code Page)
4. User enters access code
5. Code is verified against `ACCESS_CODE_HASH`
6. If valid → Grant session access
7. If invalid → Display `/denied.html` (Access Denied Page)
8. User can retry or contact administrator

### Security Features
- ✅ **Access Code Authentication** - Customizable access codes
- ✅ **Session Management** - Secure session handling
- ✅ **Request Interceptors** - Pre-request validation
- ✅ **Global Exception Handler** - Prevent information leakage
- ✅ **Input Validation** - All inputs validated via Spring Validation
- ✅ **SQL Injection Protection** - JPA parameterized queries
- ✅ **CORS Configuration** - Configurable cross-origin policies

### Access Code Setup

```properties
# application.properties
ACCESS_CODE_HASH=your_hashed_code

# Or via environment variable
export ACCESS_CODE_HASH="your_hashed_code"
```

Generate access code hash:
```bash
# Using Spring's BCryptPasswordEncoder
# Java: BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
#       String hash = encoder.encode("your_password");
```

---

## 📊 Database Schema

### Entity Relationships

```
User ──┬── TaskItem (1:Many)
       ├── SubjectItem (1:Many)
       ├── ScheduleItem (1:Many)
       ├── NoteItem (1:Many)
       └── UserMetrics (1:1)

TaskItem ────── Priority (Many:1)
SubjectItem ─── History Tracking
ScheduleItem ── Time Allocation
NoteItem ────── Content Storage
```

### Key Entities

| Entity | Purpose | Fields |
|--------|---------|--------|
| **TaskItem** | Task management | id, title, description, priority, status, createdAt, updatedAt |
| **SubjectItem** | Subject tracking | id, name, description, createdAt, history |
| **ScheduleItem** | Schedule planning | id, title, timeSlot, subject, createdAt, updatedAt |
| **NoteItem** | Note storage | id, title, content, createdAt, updatedAt |
| **UserMetrics** | Progress tracking | id, totalTasks, completedTasks, studyHours, lastUpdated |
| **Priority** | Task priority | LOW, MEDIUM, HIGH |

---

## 🛠️ Configuration Guide

### Spring Boot Properties
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Database Configuration (MySQL)
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Application Configuration
spring.application.name=augmind-app
augmind.access-code-hash=${ACCESS_CODE_HASH}
```

### Customization Options
- **Port**: Change `server.port` in `application.properties`
- **Database**: Update `spring.datasource` properties
- **Access Code**: Set `ACCESS_CODE_HASH` environment variable
- **Auto-initialization**: Enable/disable in `DataInitializer.java`

---

## 📈 Deployment

### Deploy to Render

#### Step-by-Step Deployment:

1. **Connect Repository to Render**
   - Go to [render.com](https://render.com)
   - Create new Web Service
   - Connect your GitHub repository
   - Select `Dockerfile` as build method

2. **Configure Environment Variables**
   - `ACCESS_CODE_HASH` - Your access code hash
   - `SPRING_DATASOURCE_URL` - MySQL connection string
   - `SPRING_DATASOURCE_USERNAME` - Database username
   - `SPRING_DATASOURCE_PASSWORD` - Database password
   - `PORT` - Render will assign automatically

3. **Database Setup**
   - Use external MySQL service (e.g., PlanetScale, AWS RDS)
   - Update `SPRING_DATASOURCE_URL` with your MySQL host
   - Ensure network access is configured

4. **Deploy**
   - Push to repository
   - Render automatically builds and deploys
   - Monitor logs in Render dashboard

#### Using render.yaml Blueprint:
```bash
# Render reads this automatically
# See render.yaml for pre-configured services
```

### Health Checks
- **Endpoint**: `GET /access`
- **Response**: Public access page (no authentication required)
- **Interval**: 30 seconds (configurable)

### Scaling
- **Memory**: 512MB (minimum) to 1GB (recommended)
- **vCPU**: 0.5 (minimum) to 1 (recommended)
- **Auto-scaling**: Not applicable to free tier

### 🎉 Live Instance
**Currently deployed and running at:** [https://augmind-app.onrender.com/](https://augmind-app.onrender.com/)
- Status: ✅ Active
- Accessible: 24/7
- Database: Connected and synced

---

## 🧪 Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ApiSmokeTest

# Run with coverage
mvn test jacoco:report

# View coverage report
target/site/jacoco/index.html
```

### Test Files
- `src/test/java/com/augmind/app/web/ApiSmokeTest.java` - Integration tests

## 🔗 Useful Links & Resources

### Official Documentation
- 🔗 **Spring Boot Official** - [spring.io](https://spring.io)
- 🔗 **Spring Data JPA** - [docs.spring.io/spring-data-jpa](https://docs.spring.io/spring-data-jpa)
- 🔗 **MySQL Documentation** - [dev.mysql.com](https://dev.mysql.com)
- 🔗 **Docker Documentation** - [docker.com/docs](https://docker.com/docs)
- 🔗 **Render Deployment** - [render.com/docs](https://render.com/docs)

### Project Repository
- 🔗 **GitHub Repository** - [Link to your repository]
- 🔗 **Issue Tracker** - [GitHub Issues]
- 🔗 **Pull Requests** - [GitHub Pull Requests]

### Live Application
- 🌐 **Live Demo** - [https://augmind-app.onrender.com/](https://augmind-app.onrender.com/)
- 📊 **Application Status** - Active and running
- 🚀 **Hosted on** - Render Platform

### Community & Support
- 🔗 **Spring Community Forum** - [community.spring.io](https://community.spring.io)
- 🔗 **Stack Overflow - Spring Tag** - [stackoverflow.com/questions/tagged/spring](https://stackoverflow.com/questions/tagged/spring)
- 🔗 **Spring Boot Discussions** - [github.com/spring-projects/spring-boot/discussions](https://github.com/spring-projects/spring-boot/discussions)

### Related Technologies
- 🔗 **Maven Central Repository** - [mvnrepository.com](https://mvnrepository.com)
- 🔗 **MySQL Download** - [mysql.com/downloads](https://mysql.com/downloads)
- 🔗 **Docker Hub** - [hub.docker.com](https://hub.docker.com)

---

## 👨‍💻 Author & Contributors

### Project Author
**Name**:MALIPEDDI SEKHAR  
**Email**: malipeddisekhar63@gmail.com  
**GitHub**: https://github.com/malipeddisekhar 
**LinkedIn**:(https://linkedin.com/in/malipeddisekhar  

### Contribution Guidelines
We welcome contributions! Please follow these steps:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contributors
- Contributors will be listed here
- [Contribution Guide](CONTRIBUTING.md)

---

## 📋 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

### MIT License Summary
- ✅ **Permission Granted**: Commercial use, private use, modification, distribution
- ✅ **Conditions**: License and copyright notice must be included
- ❌ **Limitation**: No liability or warranty provided
- ✅ **Free to Use**: No restrictions on usage

### License Text
```
MIT License

Copyright (c) 2026 [Your Name/Organization]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 🙏 Acknowledgments

- **Spring Boot Team** - For the excellent framework
- **MySQL Team** - For the robust database
- **Docker Team** - For containerization technology
- **Render** - For hosting and deployment platform
- **Contributors** - For their valuable contributions
- **Community** - For feedback and support

---

## 📞 Support & Contact

### Getting Help
- 🌐 **Live Demo**: [https://augmind-app.onrender.com/](https://augmind-app.onrender.com/) - Try the app now!
- 📧 **Email Support**: [malipeddisekhar63@gmail.com]
- 💬 **GitHub Issues**: [Report bugs and feature requests](https://github.com/malipeddisekhar)

### Reporting Issues
Please include:
- Detailed description of the issue
- Steps to reproduce
- Expected vs actual behavior
- Environment details (OS, Java version, etc.)
- Error logs and screenshots

---

## 📅 Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | 2026-05-11 | Initial production release |
| - | - | - |

---

## 🎯 Future Enhancements

- [ ] User authentication system
- [ ] Mobile app (iOS/Android)
- [ ] Real-time notifications
- [ ] Advanced analytics dashboard
- [ ] AI-powered task recommendations
- [ ] Collaboration features
- [ ] API rate limiting
- [ ] Advanced search functionality



*Last Updated: May 11, 2026*  
*Version: 1.0.0*
- `GET /schedule`
- `POST /schedule`
- `DELETE /schedule/{id}`
- `GET /notes`
- `POST /notes`
- `GET /stats`
- `POST /stats/sessions/increment`

## Notes
- Tables are created automatically by JPA (`ddl-auto=update`).
- Default subjects and schedule entries are seeded automatically on first run.
- UI now supports adding and deleting tasks, subjects, schedules, and notes dynamically.

  ## License
This project is developed for educational and learning purposes.

## Author
Developed by Malipeddi Sekhar

## Copyright
© 2026 Malipeddi Sekhar. All rights reserved.

Permission is granted to use, modify, and distribute this project for academic and personal use only.
