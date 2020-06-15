### Introduction 
This is spring elastic poc is micro service application to demonstrate how to use spring boot with MongoDB GridFS.

### Getting Started
To setup, build and run poc application in LOCAL environment. 
1.	Clone or download from github (https://github.com/ravireddy76/docker-compose)
2.	Open Project (spring-gridfs-poc) in IntelliJ/Eclipse 
3.  For lombok plugin configuration please follow steps https://www.baeldung.com/lombok-ide
3.	Build ``` ./gradlew clean build ```
4.	Run the application by right click  ClusterApplication class 
    OR using gradle command  ``` ./gradlew bootRun ```

### Endpoint Information
Swagger Documentation : http://localhost:8085/swagger-ui.html
- Create:
```
 create bigfile > 20MB : 
    POST -> http://localhost:8085/gridfspoc/bigfile

 create image file : 
    POST -> http://localhost:8085/gridfspoc/image

 create video file : 
    POST -> http://localhost:8085/gridfspoc/video

    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
    Body -> {
                 "fileName": "test"
             }
```

### Jar file execution option 2 run application
 - Open command line prompt
 - Go to (cd to) project folder
 - Go to build/libs directory
 - execute command  ``` java -jar spring-gridfs-poc.jar ```
 - http://localhost:8085/swagger-ui.html

### Docker Option 2 run application
 - To build docker image from docker file
   ``` docker build -t spring-gridfs-poc .```
 - To run created docker image  ``` docker run -p 8085:8085 -t spring-gridfs-poc ```
 - http://localhost:8085/swagger-ui.html
 
### Contribute
Ravi Reddy (Ravinder Nancherla)

### License
@CopyRight ( C ) All rights reserved to Ravi Reddy POC World. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)
