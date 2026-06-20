# Use Eclipse Temurin JRE 17 Alpine for a lightweight runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the pre-built JAR from the target folder
# Note: This requires the build stage (mvn package) to run first on the host/Jenkins agent
COPY target/java-microservice-1.0.0.jar app.jar

# Expose port (if applicable)
EXPOSE 8080

# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]
