FROM openjdk:11
#FROM cafapi/java-postgres
ADD target/backend-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]