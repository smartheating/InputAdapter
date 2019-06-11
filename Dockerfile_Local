FROM java:8-jdk-alpine
VOLUME /tmp
EXPOSE 9013
ARG JAR_FILE=target/inputAdapter-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} inputAdapter.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/inputAdapter.jar"]