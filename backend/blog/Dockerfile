#docker image
FROM store/oracle/jdk:11
ADD target/blog-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]