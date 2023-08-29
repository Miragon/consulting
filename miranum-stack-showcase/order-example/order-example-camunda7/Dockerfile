FROM amazoncorretto:17.0.5
VOLUME /tmp
COPY target/*.jar app.jar
COPY src/main/resources/application.yaml /application.yaml
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]