package com;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

    private static final Properties PRODUCER_PROPS = new Properties();

    static {
        //Producer properties
        //Change the host name if the kafka is not installed in the local machine
        PRODUCER_PROPS.put("metadata.broker.list", System.getenv("KAFKA_HOST"));
        PRODUCER_PROPS.put("bootstrap.servers", System.getenv("KAFKA_HOST"));

        PRODUCER_PROPS.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        PRODUCER_PROPS.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        PRODUCER_PROPS.put("reconnect.backoff.ms", "1000");
        PRODUCER_PROPS.put("connect.timeout.ms", "3000");
        PRODUCER_PROPS.put("producer.type", "async");
        PRODUCER_PROPS.put("request.required.acks", "1");
        PRODUCER_PROPS.put("metadata.fetch.timeout.ms", "3000");
    }

    public static boolean send(String message) {
        KafkaProducer producer = null;
        try {
            producer = new KafkaProducer(PRODUCER_PROPS);
            //Change the topic name. Here i use KAFKA_TOPIC
            ProducerRecord record = new ProducerRecord("test", message);
            producer.send(record);
        } catch (Exception e) {
            LOGGER.error("Exception happened while sending data to kafka", e);
            e.printStackTrace();
            return false;
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
        return true;
    }

}
