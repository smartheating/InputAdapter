FROM maven:3-jdk-8
RUN git clone https://github.com/smartheating/InputAdapter.git
WORKDIR /InputAdapter/
RUN mvn clean install -DskipTests -q
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=aws","-jar","target/InputAdapter-0.0.1-SNAPSHOT.jar"]