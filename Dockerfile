# Alpine Linux with OpenJDK JRE
FROM openjdk:16-jdk-alpine

ARG JAR_FILE=target/artwork-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/artwork-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar


EXPOSE 8080


# java -jar /opt/app/app.jar
CMD ["java","-jar","app.jar"]
