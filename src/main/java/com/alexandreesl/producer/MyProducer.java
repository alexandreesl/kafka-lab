package com.alexandreesl.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer {


  private KafkaProducer producer;


  public MyProducer() {

    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", "localhost:32768,localhost:32769");
    kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    kafkaProps.put("acks", "all");
    producer = new KafkaProducer<String, String>(kafkaProps);

  }


  public void sendMessage(String topic, String key, String message) throws Exception {

    ProducerRecord<String, String> record = new ProducerRecord<>(topic,
        key, message);
    try {
      producer.send(record).get();
    } catch (Exception e) {
      throw e;
    }

  }
}
