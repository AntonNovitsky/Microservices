FROM java:8-jre

ADD ./target/hello-server.jar /app/
ENTRYPOINT ["java","-jar","/app/hello-server.jar"]

EXPOSE 8098