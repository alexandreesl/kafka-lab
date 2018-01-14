package com.alexandreesl.consumer;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyConsumer {

  private KafkaConsumer<String, String> consumer;

  public MyConsumer() {

    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", "localhost:32768,localhost:32769");
    kafkaProps.put("group.id", "MyConsumerGroup");
    kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    kafkaProps
        .put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    consumer = new KafkaConsumer<String, String>(kafkaProps);

  }

  public void consume() {

    consumer.subscribe(Collections.singletonList(" test"));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
          System.out.println("Key: " + record.key());
          System.out.println("Value: " + record.value());
        }
      }
    } finally {
      consumer.close();
    }


  }

}
