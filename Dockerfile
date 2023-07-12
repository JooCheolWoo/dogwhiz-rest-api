FROM openjdk:11-jdk

EXPOSE 10800

WORKDIR /app

COPY ./build/libs/dogwhiz-rest-api-0.0.1-SNAPSHOT.jar ./dogwhiz-rest-api.jar

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=$PROFILE -jar dogwhiz-rest-api.jar"]

