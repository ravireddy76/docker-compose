FROM openjdk:8
ADD build/libs/reporting-apis.jar reporting-apis.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "reporting-apis.jar"]docker build -t