FROM openjdk:8
ADD build/libs/micronaut-mongodb-poc.jar micronaut-mongodb-poc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "micronaut-mongodb-poc.jar"]docker build -t