FROM openjdk:8
ADD build/libs/micronaut-poc.jar micronaut-poc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "micronaut-poc.jar"]docker build -t