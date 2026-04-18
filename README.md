# Auraminds Spring Boot Application

Spring Boot website for task management, study subjects, focus timer tracking, schedule planning, and notes, connected directly to MySQL.

## Stack
- Java 21
- Spring Boot 3.4
- Spring Web, Validation, Data JPA
- MySQL 8+
- Docker + Docker Compose

## Project Structure
- Backend application: `src/main/java/com/augmind/app`
- Frontend UI: `src/main/resources/static/index.html`

### Clean Spring Layout
```text
src/
   main/
      java/com/augmind/app/
         config/
         domain/
         dto/
         repository/
         service/
         web/
         AugmindApplication.java
      resources/
         static/
            index.html
         application.properties
   test/
      java/com/augmind/app/web/
```

## Run Locally (MySQL)
1. Install Java 21 and MySQL 8+.
2. Create a MySQL database named `augmind`.
3. Set the database environment variables:
   - `DB_URL=jdbc:mysql://localhost:3306/augmind?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
   - `DB_USER=root`
   - `DB_PASSWORD=root`
4. Start the Spring Boot app with:
   ```bash
   mvn spring-boot:run
   ```
5. Open the app in the browser:
   - http://localhost:8080

## Spring Boot Setup Guide
1. Open the project in VS Code or IntelliJ.
2. Check the Spring Boot entry point at `src/main/java/com/augmind/app/AugmindApplication.java`.
3. Review the database config in `src/main/resources/application.properties`.
4. Make sure the datasource values point to your MySQL server.
5. Run the app and confirm that JPA creates the tables automatically.
6. If you change code, restart the app so Spring reloads the updated classes.

## MySQL Database Setup Guide
1. Install MySQL 8 or use a hosted MySQL service.
2. Create a database called `augmind`.
3. Use the schema automatically created by JPA on first startup.
4. Keep `spring.jpa.hibernate.ddl-auto=update` so the tables are created and adjusted automatically.
5. If the app cannot connect, verify host, port, username, password, and network access.

## Default MySQL Configuration
- `DB_URL=jdbc:mysql://localhost:3306/augmind?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- `DB_USER=root`
- `DB_PASSWORD=root`
- `PORT=8080`

## Run with Docker Compose
```bash
docker compose up --build
```

## Deploy to Render
This project is ready for a Render Docker web service.

1. Connect the repository to Render or use the included `render.yaml` blueprint.
2. Deploy the `augmind-app` web service from `Dockerfile`.
3. Set these environment variables in Render:
   - `ACCESS_CODE_HASH`
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
   - Optional aliases supported by the app: `DB_URL`, `DB_USER`, `DB_PASSWORD`
4. Point the datasource URL at an external MySQL database. Render does not provide a managed MySQL service, so the app must connect to a MySQL host you control.
5. The service health check should use `/access`, which returns the public access page and does not require an authenticated session.

The app listens on Render's assigned `PORT` automatically.

## Dynamic Website Routes Used Internally
- `GET /tasks?status=all|active|completed`
- `POST /tasks`
- `PATCH /tasks/{id}/toggle`
- `DELETE /tasks/{id}`
- `GET /subjects`
- `POST /subjects`
- `DELETE /subjects/{id}`
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
