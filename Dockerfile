FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=app/target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n", "-jar", "/application.jar"]