FROM openjdk:22-jdk
LABEL authors="satyam"
ADD target/customer-service.jar customer-services.jar


ENTRYPOINT ["java", "-jar","/customer-services.jar"]