FROM java:8-jre

ADD ./target/eureka-server-1.0-SNAPSHOT.jar /app/
ENTRYPOINT ["java","-jar","/app/eureka-server-1.0-SNAPSHOT.jar"]

EXPOSE 8070