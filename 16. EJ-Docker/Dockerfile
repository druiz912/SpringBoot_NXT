FROM openjdk:18
EXPOSE 8080
ARG JAR_FILE=target/docker-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring.jar
ENTRYPOINT ["java","-jar","/spring.jar"]