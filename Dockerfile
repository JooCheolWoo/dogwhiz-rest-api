FROM openjdk:11-jdk

COPY ./build/libs/dogwhiz-0.0.1-SNAPSHOT.jar ../dogwhiz-rest-api.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/dogwhiz-rest-api.jar"]