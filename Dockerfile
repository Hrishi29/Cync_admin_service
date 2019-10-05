FROM openjdk:latest

MAINTAINER Hrishi Gadkari "contact@programmers.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./target/hrishi-admin-service-0.0.1.jar hrishiservice.jar

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "hrishiservice.jar"]
