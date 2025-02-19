# Use OpenJDK 21 slim as the base image
FROM gradle:8.4-jdk21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the build files (e.g., build.gradle, settings.gradle, etc.)
COPY build.gradle .
COPY settings.gradle .
COPY application/src ./src

# Run Gradle clean and build
RUN gradle clean build

# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy built application JAR to container
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port it is only for Documentation purpose
EXPOSE 8087

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]