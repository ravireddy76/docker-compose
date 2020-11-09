### Introduction
This is reporting data enrichment service to enrich the reporting data into defined data mart store.

### Getting Started
To set up, build and run poc application in LOCAL environment.
1.	Clone or download project
2.	Open Project reporting-data-enrichment-service in IntelliJ/Eclipse
3.  For lombok plugin configuration please follow steps https://www.baeldung.com/lombok-ide
3.	Build ``` ./gradlew clean build ```
4.	Run the application by right click ReportingEnrichmentApplication class
    OR using gradle command  ``` ./gradlew bootRun ```

### Endpoint Information
Swagger Documentation : http://localhost:8085/swagger-ui.html
- Health Check:
```
 Heart beat end point :
    GET -> http://localhost:8085/dataenrichment/heartbeat
    Headers -> "Content-Type":"application/json"
               "Accept-Charset":"UTF-8"
```
### Architecture flow for reporting-data-enrichment-service
## ![reporting-data-enrichment-service-logo](misc/report-data-enrichment.png)

### Jar file execution option 2 run application
 - Open command line prompt
 - Go to (cd to) project folder
 - Go to build/libs directory
 - execute command  ``` java -jar reporting-data-enrichment-service.jar ```
 - http://localhost:8085/swagger-ui.html

### Docker Option 2 run application
 - To build docker image from docker file
   ``` docker build -t reporting-data-enrichment-service .```
 - To run created docker image  ``` docker run -p 8085:8085 -t reporting-data-enrichment-service ```
 - http://localhost:8085/swagger-ui.html

### Contribute
Ravi Reddy (Ravinder Nancherla)

### License
@CopyRight ( C ) All rights reserved to UHC E&A Team. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)
