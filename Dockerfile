FROM openjdk:11-jdk

EXPOSE 10800

WORKDIR /app

COPY /home/galaxypoby/projects/dogwhiz-rest-api/build/libs/dogwhiz-rest-api-0.0.1-SNAPSHOT.jar /app/dogwhiz-rest-api.jar

ENTRYPOINT ["java", "-jar", "dogwhiz-rest-api.jar"]
