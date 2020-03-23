### Introduction 
This is cluster system service is micro service application to create data points, cluster information and search cluster information by id.

### Getting Started
To setup, build and run poc application in LOCAL environment. 
1.	Clone or download from github (https://github.com/ravireddy76/cluster-system-service)
2.	Open Project in IntelliJ/Eclipse
3.  For lombok plugin configuration please follow steps https://www.baeldung.com/lombok-ide
3.	Build ``` ./gradlew clean build ```
4.	Run the application by right click  ClusterApplication class 
    OR using gradle command  ``` ./gradlew bootRun ```

### Endpoint Information
Swagger Documentation : http://localhost:8085/swagger-ui.html
- Create:
```
 create data points and clusters : 
    POST -> http://localhost:8085/cspoc/create
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
    Body -> {
                 "maxCoordinate": 20,
                 "minCoordinate": 0,
                 "numClusters": 4,
                 "numPoints": 20
             }
```
```
 get by cluster id : 
    GET -> http://localhost:8085/cspoc/iteration/{iterationId}/cluster/{clusterId}
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
```

### Jar file execution option 2 run application
 - Open command line prompt
 - Go to (cd to) project folder
 - Go to build/libs directory
 - execute command  ``` java -jar cluster-system-service.jar ```
 - http://localhost:8085/swagger-ui.html

### Docker Option 2 run application
 - To build docker image from docker file
   ``` docker build -t cluster-system-service .```
 - To run created docker image  ``` docker run -p 8085:8085 -t cluster-system-service ```
 - http://localhost:8085/swagger-ui.html
 
### Contribute
Ravi Reddy (Ravinder Nancherla)

### License
@CopyRight ( C ) All rights reserved to Ravi Reddy POC World. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)
