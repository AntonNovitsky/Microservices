FROM java:8-jre

ADD ./target/eureka-server.jar /app/
ENTRYPOINT ["java","-jar","/app/eureka-server.jar"]

EXPOSE 8070