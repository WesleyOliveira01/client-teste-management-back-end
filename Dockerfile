FROM maven:latest AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/todo.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
