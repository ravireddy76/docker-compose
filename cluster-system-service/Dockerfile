FROM openjdk:8
ADD build/libs/cluster-system-service.jar cluster-system-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cluster-system-service.jar"]docker build -t