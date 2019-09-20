FROM java:8-jre

ADD ./target/zuul-server.jar /app/
ENTRYPOINT ["java","-jar","/app/zuul-server.jar"]

EXPOSE 8079