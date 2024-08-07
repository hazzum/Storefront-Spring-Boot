# Use an official Java 21 runtime as a parent image
FROM eclipse-temurin:19-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/storefront-0.0.1-SNAPSHOT.jar app.jar

# Make port 4200 available to the world outside this container
EXPOSE 4200

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]