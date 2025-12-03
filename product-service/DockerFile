# ---------- Stage 1: Build the application ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the pom.xml and resolve dependencies first (for caching)
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copy the rest of the project
COPY src ./src

# Package the application (skip tests for faster build)
RUN mvn -q clean package -DskipTests


# ---------- Stage 2: Run the application ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built JAR from the build image
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render ignores this but good practice)
EXPOSE 8080

# Start the Spring Boot app
