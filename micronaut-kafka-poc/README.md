### Introduction
This is POC application using micronaut framework to build micro services.

### Getting Started
To setup, build and run poc application in LOCAL environment.
1.	Clone or download from github
2.	Open Project in IntelliJ
3.	Build ``` ./gradlew clean build ```
4.	Run the application by right click  POC application class.
5.  Install or Use any docker image to run zoo keeper and kafka
6.  Create a topic in kafka called "orders"

### Endpoint Information
- Order:
```
 create Order :
    POST -> http://localhost:8080/kafkapoc/order
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
    Body -> {
               "id": "ORDER1234",
               "type": "iPad",
               "address": "123 Street Dr, MN, Ediana 55426",
               "status": "In Process",
               "createdDate": "2020-01-30 12:31:21"
             }
```
```
 get order :
    GET -> http://localhost:8080/kafkapoc/order/{ORDERID}
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
```

### Docker
 - To build docker image from docker file
   ``` docker build -t micronaut-poc .```
 - To run created docker image  ``` docker run <imageId> ```


### Contribute
Ravi Reddy

### License
@CopyRight ( C ) All rights reserved to Ravi Reddy POC World. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)
