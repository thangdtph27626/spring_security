FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/project-0.0.1-SNAPSHOT-plain.jar
ADD ${JAR_FILE} ./manager-user.jar
ENTRYPOINT ["java","-jar","./manager-user.jar"]
