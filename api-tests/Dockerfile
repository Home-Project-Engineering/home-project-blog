#docker image
FROM store/oracle/jdk:11
ADD target/api-tests-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]