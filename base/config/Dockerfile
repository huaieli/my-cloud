FROM openjdk:8-jre-slim

MAINTAINER xsn

ENV PARAMS = "--server.port=0  --eureka.client.service-url.defaultZone=http://my-eureka1:10100/eureka,http://my-eureka2:10100/eureka"

ADD target/config-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]