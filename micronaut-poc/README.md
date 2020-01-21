### Introduction 
This is POC application using micronaut framework to build micro services.

### Getting Started
To setup, build and run poc application in LOCAL environment. 
1.	Clone or download from github
2.	Open Project in IntelliJ
3.	Build ``` ./gradlew clean build ```
4.	Run the application by right click  POC application class.

### Endpoint Information
- User:
```
 create user : 
    POST -> http://localhost:8080/demopoc/user
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
    Body -> {
               "userId": "USR1234",
               "password": "XYZ#46@827H"
             }
```
```
 create user : 
    GET -> http://localhost:8080/demopoc/user/{USRID}
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
```

### Docker
 - To build docker image from docker file
   ``` docker build -t micronaut-poc```
 - To run created docker image  ``` docker run <imageId> ```
 
 
### Contribute
Ravi Reddy 

### License
@CopyRight ( C ) All rights reserved to Ravi Reddy POC World. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)