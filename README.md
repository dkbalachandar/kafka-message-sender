A Rest application send message to Kafka broker

1. Clone this repository
2. Open up pom.xml file and search for ADVERTISED_HOST and update ??? with your machine domain name 
2. Build it and run by mvn docker:start
3. Call the service by accessing http://localhost:8080/api/kafka/send?msg=test data
4. Use flume agent to validate the content. Refer the project https://github.com/dkbalachandar/flume-kafka-agent or run the below command to validate the message

I assume that Docker is installed in the machine


