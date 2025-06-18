# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine
EXPOSE 9000
# Copy the JAR file to the container
ADD target/AddressService-0.0.1-SNAPSHOT.jar AddressService-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","AddressService-0.0.1-SNAPSHOT.jar" ]