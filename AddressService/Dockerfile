# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Expose the port your app listens on
EXPOSE 9000

# Copy the JAR file to the container and rename na "app.jar"
COPY target/AddressService-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
