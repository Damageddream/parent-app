# 1) Build Stage
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy only the Gradle wrapper, build scripts, and settings first.
# These layers will be cached unless these files change.
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Run a simple Gradle command to download the Gradle distribution.
RUN ./gradlew --version

# Now copy the rest of the project files.
COPY . .

# Build the project.
RUN ./gradlew clean build --no-daemon

# 2) Final Stage
FROM eclipse-temurin:21-jdk-alpine
LABEL app=parent-app
WORKDIR /app

# Adjust the JAR name if needed.
COPY --from=builder /app/build/libs/parent-app-0.0.1-SNAPSHOT.jar .

EXPOSE 7070
ENTRYPOINT ["java", "-jar", "parent-app-0.0.1-SNAPSHOT.jar"]