# Woven File Storage System

This is used to upload/download/delete the files from system.
The file content is located into logical disk. The system is exposed via API.

## Summary
1. Functionalities
   - 1.1 File Upload
   - 1.2 File Download
   - 1.3 File Cleanup
2. Getting Started (Prerequisites, Installing)
    - 2.1 Prerequisites
    - 2.2 Database installation
        - 2.2.1 H2

## 1. Functionalities 

Clone or download a copy of this project.

### 2.1 Prerequisites

This project requires Java 11, Maven and at least one database (H2).

### 2.2 Database installation

#### 2.2.1 H2
No installation is required.
The `spring.datasource.url` is the one required property which should be set. By default, the 
username is `sa` with empty password. Two modes: in memory and file storage. See the `application.properties`
file for more details related configuration.
To access default H2 Console use this link: http://localhost:8082/h2-console/ , where 8082 is the server port.
db url: jdbc:h2:file:./data/db
To create the JAR file please use the following command:
```
mvn clean package
```

## 3. Running the tests

All available unit / integration tests are in package: `src/test/java`.
The main rule is: one unit test class for each java class.

## 4. Deployment

If the build (the jar file) is ready then the application can be run. Please, use the following command to run the application:
```
mvn clean
mvn install
java -jar target/woven-fileupload-0.0.1-SNAPSHOT.jar
```

## 5. Swagger usage
You can use swagger for testing proposes:
```
URL: APP_HOST/swagger-ui.html  . For example:  http://localhost:8082/swagger-ui.html
```