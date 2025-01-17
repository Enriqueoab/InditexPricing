FROM maven:3.8.4-openjdk-17-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM amazoncorretto:17-alpine3.17
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 3000

ENTRYPOINT ["java","-jar","app.jar"]