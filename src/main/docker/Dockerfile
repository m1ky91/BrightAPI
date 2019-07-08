FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY bright-api.jar bright-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bright-api.jar"]