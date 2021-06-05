FROM openjdk:8-jdk-alpine
COPY ./target/spring-security-demo-0.0.1-SNAPSHOT.jar /usr/src/demo/
WORKDIR /usr/src/demo
EXPOSE 8081
CMD ["java", "-jar", "spring-security-demo-0.0.1-SNAPSHOT.jar"]