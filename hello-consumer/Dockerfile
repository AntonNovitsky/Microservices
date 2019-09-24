FROM java:8-jre

ADD ./target/hello-consumer.jar /app/
ENTRYPOINT ["java","-jar","/app/hello-consumer.jar"]

EXPOSE 8082