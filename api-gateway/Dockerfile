FROM openjdk:22-jdk
LABEL authors="satyam"

ADD ./target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java", "-jar","api-gateway.jar"]