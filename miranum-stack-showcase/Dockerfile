FROM amazoncorretto:17-alpine

COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY order-example ./order-example

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]
