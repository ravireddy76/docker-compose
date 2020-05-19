FROM openjdk:8
ADD build/libs/spring-elastic-poc.jar spring-elastic-poc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-elastic-poc.jar"]docker build -t