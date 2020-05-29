FROM openjdk:8-jre-slim

MAINTAINER xsn

ENV PARAMS = "--server.port=0 --spring.profiles.active=docker"

ADD target/activemq-provider-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]