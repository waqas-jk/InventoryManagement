# Product Warehouse Inventory management Application

This application is developed using `Spring Boot` (version 1.5.10). 
   
# Enviroment Requirememnt
  Following must be installed on system:
  1. JDK 8 - `JAVA_HOME` should be set to the JDK8 installaion directory
  2. Maven 3.x - `M2_HOME` should be set to Maven installation direrctory.
  3. Tomcat 8 (Optional). This application uses embeded tomcat 8 server, therefore no need of installing tomcat.
  4. Database MySql 5 
  5. To execute commands subsequent sections commandline or terminal can be used.

# Build
  To build the project execute `mvn clean install` command from project's home directory.
 
# Create Ware
  War can be generated using command `mvn clean package`. This WAR can be deployed Apache Tomcat.
 
# Database Setup
  Default schema name is `pwi`. However it can be changed in `application.properties` in property `spring.datasource.url`. 
 
  Before starting application following steps should be performed:
   - Empty Schema MUST be created in database before starting application.
   - Modify database username/password in `application.properties`.
 
# Start Application 

After successful build, application can be started using command `mvn spring-boot:run` from project's home directory.

Application is deployed on port `8080` by default. 

# Execute Unit Tests 
  - Test can be compiled from commandline/terminal using command `mvn test-compile`.
  - Tests can be executed using command `mvn test`
  
# Clients To test REST services
 Following clients can be used to test the REST webservice in application.
  - PostMan extension for Chrome (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)
  - SOAPUI (https://www.soapui.org)
  
  

