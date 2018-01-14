package com.alexandreesl.consumer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyConsumer {

  private KafkaConsumer<String, String> consumer;

  public MyConsumer() throws UnknownHostException {

    InetAddress ip = InetAddress.getLocalHost();

    StringBuilder builder = new StringBuilder();
    builder.append(ip.getHostAddress());
    builder.append(":");
    builder.append("32786");
    builder.append(",");
    builder.append(ip.getHostAddress());
    builder.append(":");
    builder.append("32787");


    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", builder.toString());
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
