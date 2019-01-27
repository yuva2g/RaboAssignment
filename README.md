## RaboAssignment
A project for Banks to process files (CSV or XML) and finding the failed transactions based on conditions

# Unit Test
Unit testing is done through JUnit and mockito, Devops team can run mvn test to run all the unit tests in Jenkins

# Sonar
Project is currently configured with SonarLint in STS IDE. SonarQube can be configured by Devops to run while merging to staging.

# Integration Test
POSTMA, Swagger are some of the ways to do integration test, make sure the project is running while testing with these tools.

# Documentation
Swagger is used for the API documentation purpose

# Build & Run
Run mvn spring-boot:run to start the project

# Deployment
Devops team can use Jenkins to automate the Build and Deployment.

# Future enhancements
# Asynynchronous
File processing can be done asynchronously as it would be easy for user when processing a file with large size.

# Messaging
To support the asynchronous processing a Message broker like Apache Kafka or RabbitMQ would be helpful.

# IFileProcessor - Conditional basis bean injection
There could be many files to become processed in future, so an interface with a common goal is created and injected to the controller and the processing of each file happends based on the implementation provided.
