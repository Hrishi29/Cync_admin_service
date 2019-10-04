FROM openjdk:latest

MAINTAINER Hrishi Gadkari "contact@programmers.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./target/hrishi-admin-service-1.0.0.jar service.jar

CMD ["java", "-jar", "service.jar"]
