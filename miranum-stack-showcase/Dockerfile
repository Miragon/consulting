FROM amazoncorretto:17-alpine

COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY order-example ./order-example

RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run"]
