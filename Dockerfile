FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Cache dependencies separately so rebuilds are faster
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

# Build the application — no || true so a failed build stops the image
COPY src ./src
RUN mvn -q -DskipTests clean package

# ---- Runtime image ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S -G spring spring

COPY --from=builder /workspace/target/augmind-app-1.0.0.jar app.jar

USER spring:spring
EXPOSE 8080

ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Dspring.main.banner-mode=off", \
  "-Dspring.boot.logStartupInfo=false", \
  "-jar", "/app/app.jar"]
