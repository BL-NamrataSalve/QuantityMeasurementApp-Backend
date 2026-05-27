# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the package
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root group and user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built jar file
COPY --from=builder /app/target/api-gateway-1.0-SNAPSHOT.jar app.jar

# Expose port (Render uses port 10000 by default for web services)
EXPOSE 10000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
