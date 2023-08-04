FROM openjdk:17
EXPOSE 8090
ADD target/jinking-docker.jar jinking-docker.jar
ENTRYPOINT ["java", "-jar", "jinking-docker.jar"]
