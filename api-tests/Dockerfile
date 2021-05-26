#docker image
FROM openjdk:11
ADD target/api-tests-1.1.1.jar app-test.jar
ENTRYPOINT ["java", "-jar", "app-test.jar"]