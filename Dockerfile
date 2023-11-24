FROM openjdk:11

ADD  target/aoer-0.0.1-SNAPSHOT.jar aoer-0.0.1-SNAPSHOT.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "aoer-0.0.1-SNAPSHOT.jar"]
