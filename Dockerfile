# ============================
# 🏗️ Stage 1: Build the Spring Boot JAR
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (caches dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source code
COPY src ./src

# Build the application (skip tests to speed up builds)
RUN mvn clean package -DskipTests


# ============================
# 🚀 Stage 2: Run the built JAR
# ============================
FROM eclipse-temurin:17-jdk-jammy


# Set working directory
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render will map it automatically)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
