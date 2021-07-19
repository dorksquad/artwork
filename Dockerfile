# Alpine Linux with OpenJDK JRE 16 (must be atleast 16+)
FROM openjdk:16-jdk-alpine

# create an argument in the docker image for the jar file
ARG JAR_FILE=target/artwork-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/artwork-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# expose the port 8080 for the service
EXPOSE 8080

# java -jar /opt/app/app.jar This tells java to run the jar file artwork-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","app.jar"]
