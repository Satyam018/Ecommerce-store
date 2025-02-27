FROM openjdk:22-jdk
LABEL authors="satyam"

ADD ./target/auth-services.jar auth-services.jar
ENTRYPOINT ["java", "-jar","auth-services.jar"]