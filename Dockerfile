FROM ubuntu:latest

LABEL authors="sergejus"

RUN apt-get update &&  \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY pom.xml /app
COPY src /app/src

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/paskaita_2024_07_01_reservation-0.0.1-SNAPSHOT.jar"]