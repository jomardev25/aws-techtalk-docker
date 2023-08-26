FROM openjdk:17-alpine
ARG JAR_FILE="/target/gleam-post-0.0.1-SNAPSHOT.jar"
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
EXPOSE 5432
ENTRYPOINT ["java", "-jar", "/app/app.jar"]