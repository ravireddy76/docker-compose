FROM openjdk:8
ADD build/libs/enrichment-service.jar enrichment-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "enrichment-service.jar"]docker build -t