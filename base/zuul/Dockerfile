FROM openjdk:8-jre-slim

MAINTAINER xsn

ENV PARAMS = "--spring.profiles.active=docker"

ADD target/zuul-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]