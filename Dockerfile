FROM openjdk:8-jdk-alpine
COPY ./target/spring-security-demo-0.0.1-SNAPSHOT.jar /usr/src/demo/
WORKDIR /usr/src/demo
EXPOSE 8080
CMD ["java", "-jar", "spring-security-demo-0.0.1-SNAPSHOT.jar"]