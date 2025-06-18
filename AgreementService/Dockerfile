# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine
EXPOSE 9001
# Copy the JAR file to the container
ADD target/AgreementService-0.0.1-SNAPSHOT.jar AgreementService-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","AgreementService-0.0.1-SNAPSHOT.jar" ]