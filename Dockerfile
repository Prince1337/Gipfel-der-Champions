# Use a base image with both Gradle and Java 17
FROM gradle:8.5-jdk17 as builder

WORKDIR /app

# Copy the build.gradle file
COPY build.gradle .

# Copy the source code
COPY src/ ./src/

# Build the application
RUN gradle bootJar --no-daemon

# Use OpenJDK 17 for the runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/build/libs/*.jar ./app.jar

# Run the jar file
CMD ["java", "-jar", "app.jar"]