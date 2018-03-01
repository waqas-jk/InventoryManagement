# Product Warehouse Inventory management Application

This application is developed using `Spring Boot` (version 1.5.10). 

# Assumptions

   
# Enviroment Requirememnt
  Following must be installed on system:
  1. JDK 8 - `JAVA_HOME` should be set to the JDK8 installaion directory
  2. Maven 3.x - `M2_HOME` should be set to Maven installation direrctory.
  3. To execute commands subsequent sections commandline or terminal can be used.
  4. Tomcat 8 (Optional). This application uses embeded tomcat 8 server.
  
  
# Build
  To build the project execute `mvn clean install` command from project's home directory.
 
# Start Application 

After successful build, application can be started using command `mvn spring-boot:run` from project's home directory.

If installed on local system, 
server name = localhost
port = 8080

# Execute Unit Tests 
  - Test can be compiled from commandline/terminal using command `mvn test-compile`.
  - Tests can be executed using command `mvn test`
  
# Clients To test REST services
 Following clients can be used to test the REST webservice in application.
  - PostMan extension for Chrome (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)
  - SOAPUI (https://www.soapui.org)
  
  

