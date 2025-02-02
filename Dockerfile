
# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy built application JAR to container
COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8087

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]