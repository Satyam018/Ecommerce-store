FROM openjdk:22-jdk
LABEL authors="satyam"

ADD target/payment-services.jar payment-services.jar
ENTRYPOINT ["java", "-jar","payment-services.jar"]