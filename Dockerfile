#FROM maven:3.9-amazoncorretto-17 as builder
#
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#
#RUN mvn -f /usr/src/app/pom.xml clean package
#
##
## Use a minimal image for runtime
FROM amazoncorretto:21-alpine-jdk

# Set working directory
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Copy the built JAR file from the build stage

# Expose the port your application listens on
EXPOSE 8080

# Run your application
CMD ["java","-Djasypt.encryptor.password=secretkey","-jar", "app.jar"]