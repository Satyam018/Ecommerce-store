FROM openjdk:22-jdk
LABEL authors="satyam"

ADD /target/product-services.jar product-services.jar

ENTRYPOINT ["java", "-jar","product-services.jar"]