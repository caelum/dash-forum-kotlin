FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/dash_forum/dash-forum-kotlin.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/dash_forum/dash-forum-kotlin.jar"]