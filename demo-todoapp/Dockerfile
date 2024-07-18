# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copying necessary files
COPY demo-todoapp/pom.xml .
COPY demo-todoapp/src ./src/

# Ensure mvnw is executable
RUN chmod +x mvnw

# Clean, install dependencies, and package
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Stage 2: Run the application
FROM openjdk:17.0.2-jre-slim

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (adjust accordingly if your application uses a different port)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
