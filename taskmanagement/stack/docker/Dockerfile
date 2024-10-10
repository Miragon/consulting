FROM maven:3.9.8-amazoncorretto-21 AS build

ARG PROJECT

WORKDIR /app

# Copy everything defined by the .dockerignore file
COPY . .

RUN echo "Building project: ${PROJECT}"
RUN mvn package -pl ${PROJECT} -am -DskipTests

FROM amazoncorretto:21-alpine

ARG PORT
ARG PROJECT

ENV PORT=${PORT}

EXPOSE ${PORT}

WORKDIR /app

COPY --from=build /app/${PROJECT}/target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
