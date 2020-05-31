FROM openjdk:8-jre-slim

MAINTAINER xsn

ENV PARAMS = "--eureka.client.service-url.defaultZone=http://my-eureka1:10101/eureka,http://my-eureka2:10101/eureka"

ADD target/config-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]