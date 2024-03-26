# Use a base image with Java and other dependencies for running Spring Boot applications
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/to-do-list-0.0.1-SNAPSHOT.jar /app/app.jar

# Specify the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
