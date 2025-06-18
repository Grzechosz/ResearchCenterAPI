# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine
EXPOSE 9003
# Copy the JAR file to the container
ADD target/UserService-0.0.1-SNAPSHOT.jar UserService-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","UserService-0.0.1-SNAPSHOT.jar" ]