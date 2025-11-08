# Use an official OpenJDK image as base
FROM openjdk:26-ea-trixie
# Set working directory inside container
WORKDIR /app

# Copy the built jar file from target folder
COPY target/todowithsecurity-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
