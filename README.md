# AddressBookAPI
This is a API based application to manage an address book

## For building and running the application you need:

**JDK 1.8**

**Gradle 7.1.1**

## Running the application locally

Import the project locally as a gradle project onto your favourite IDE. Run the main method in the AddressBookApplication class.

Since the application is built using Spring Boot, it uses the embedded Tomcat container to run the app.

## API Documentation

Once the application is up and running, you can access the Swagger Documentation via the below link:

http://localhost:8080/swagger-ui.html

## Build

gradle clean build

## Automated Test Suite

Unit tests and Integration tests are written using JUNIT and are automated to run during the build process.

## Libraries

Since it is a spring boot application, all libraries come packaged as part of the application jar
