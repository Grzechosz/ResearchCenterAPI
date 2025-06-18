# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine
EXPOSE 9002
# Copy the JAR file to the container
ADD target/ProjectService-0.0.1-SNAPSHOT.jar ProjectService-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","ProjectService-0.0.1-SNAPSHOT.jar" ]