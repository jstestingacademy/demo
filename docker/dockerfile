# Use a base image with Java
FROM maven:3.9.5-eclipse-temurin-17 AS builder

# Set work directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN mvn clean install

# Use a lightweight JRE for execution
FROM eclipse-temurin:17-jre

# Set work directory
WORKDIR /app

# Copy built files from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Command to run tests
CMD ["java", "-jar", "app.jar"]