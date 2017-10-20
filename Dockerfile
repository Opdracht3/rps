FROM openjdk:8-jdk-slim

EXPOSE 8080

#install Spring Boot artifact
VOLUME /tmp
ADD /bin/libs/spring-rps-service-1.0.jar spring-rps-service-1.0.jar
RUN sh -c 'touch /spring-rps-service-1.0.jar'
ENTRYPOINT ["java","-jar","/spring-rps-service-1.0.jar"]
