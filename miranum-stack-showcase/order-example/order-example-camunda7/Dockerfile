FROM ubuntu:24.10
VOLUME /tmp
COPY target/*.jar app.jar
COPY src/main/resources/application.yaml /application.yaml
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]