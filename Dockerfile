FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /workspace
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline 2>&1 | grep -v DEBUG || true
COPY src ./src
RUN mvn -q -DskipTests clean package 2>&1 | grep -E '(ERROR|WARNING|BUILD|jar)' || true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S -G spring spring
COPY --from=builder /workspace/target/augmind-app-1.0.0.jar app.jar
USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-Dspring.application.name=augmind", "-jar", "/app/app.jar"]
