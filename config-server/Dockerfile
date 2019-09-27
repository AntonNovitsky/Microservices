FROM java:8-jre

ADD ./target/config-server.jar /app/
ENTRYPOINT ["java","-jar","/app/config-server.jar"]

EXPOSE 8060