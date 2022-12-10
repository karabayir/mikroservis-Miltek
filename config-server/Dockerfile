FROM openjdk:17
EXPOSE 8888
ARG JAR_FILE=target/config-server.jar
ADD ${JAR_FILE} config-server.jar
ENTRYPOINT [ "java","-jar","/config-server.jar" ]