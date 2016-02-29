A Rest application send message to Kafka broker

1. Clone this repository
2. Build it and run by mvn exec:java
3. Call the service by accessing /api/kafka/send?msg=test data
4. Use flume agent to validate the content. Refer the project https://github.com/dkbalachandar/flume-kafka-agent
