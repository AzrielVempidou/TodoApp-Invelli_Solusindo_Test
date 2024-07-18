FROM openjdk:20-jdk-alpine
VOLUME /tmp
COPY target/demo-todoapp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
