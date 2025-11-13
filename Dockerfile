# ====== STAGE 1: Build the JAR ======
FROM eclipse-temurin:21-jdk AS builder

# Set working directory
WORKDIR /app

# Copy Maven config files first
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (for faster builds)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Build the JAR (skip tests to save time)
RUN ./mvnw clean package -DskipTests

# ====== STAGE 2: Run the application ======
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the built JAR from previous stage
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
