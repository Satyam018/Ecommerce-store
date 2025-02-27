FROM openjdk:22-jdk
LABEL authors="satyam"

ADD /target/order-services.jar order-services.jar

ENTRYPOINT ["java", "-jar", "order-services.jar"]