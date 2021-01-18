FROM openjdk:8

MAINTAINER cloudgyb

COPY ./target/*.jar /app.jar

EXPOSE 8080 80

ENTRYPOINT java -jar app.jar
