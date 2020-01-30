FROM openjdk:8
ADD build/libs/micronaut-kafka-poc.jar micronaut-kafka-poc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "micronaut-kafka-poc.jar"]docker build -t