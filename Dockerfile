FROM openjdk:9
ADD /target/sklad-app-0.0.1-SNAPSHOT.jar sklad-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","sklad-app-0.0.1-SNAPSHOT.jar"]


