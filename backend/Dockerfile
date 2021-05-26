#docker image
FROM openjdk:11
#FROM postgres:latest
ADD target/backend-1.1.1.jar app-backend.jar
ENTRYPOINT ["java", "-jar", "app-backend.jar"]