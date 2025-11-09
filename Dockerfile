# Step 1: Use an official JDK runtime as base
FROM eclipse-temurin:21-jdk

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the jar file into the container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port 8080 to the outside world
EXPOSE 8080

# Step 5: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
