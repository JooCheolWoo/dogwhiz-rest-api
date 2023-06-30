FROM openjdk:11-jdk

EXPOSE 80

COPY ./build/libs/dogwhiz-rest-api-0.0.1-SNAPSHOT.jar /dogwhiz-rest-api.jar

ENTRYPOINT ["java", "-jar", "/dogwhiz-rest-api.jar"]
