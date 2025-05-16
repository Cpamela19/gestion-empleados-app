FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=app.jar

COPY --from=build /app/target/*.jar ${JAR_FILE}

ENTRYPOINT ["java", "-jar", "app.jar"]