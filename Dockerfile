FROM openjdk:11-jdk

EXPOSE 10800

WORKDIR /app

COPY ../documents/dogwhiz-dev.env ../dogwhiz-dev.env
COPY ./build/libs/dogwhiz-rest-api-0.0.1-SNAPSHOT.jar ../dogwhiz-rest-api.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Djava.net.preferIPv4Stack=true", "/dogwhiz-rest-api.jar"]
