package com.alexandreesl.consumer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class MyConsumer {

  private KafkaConsumer<String, String> consumer;

  public MyConsumer() throws UnknownHostException {

    InetAddress ip = InetAddress.getLocalHost();

    StringBuilder builder = new StringBuilder();
    builder.append(ip.getHostAddress());
    builder.append(":");
    builder.append("32771");
    builder.append(",");
    builder.append(ip.getHostAddress());
    builder.append(":");
    builder.append("32772");

    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", builder.toString());
    kafkaProps.put("group.id", "MyConsumerGroup");
    kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    kafkaProps
        .put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    kafkaProps.put("enable.auto.commit", "false");
    consumer = new KafkaConsumer<String, String>(kafkaProps);

  }

  public void consume(String topic) {

    consumer.subscribe(Collections.singletonList(topic));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);

        for (ConsumerRecord<String, String> record : records) {
          System.out.println("Key: " + record.key());
          System.out.println("Value: " + record.value());

          HashMap<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

          offsets.put(new TopicPartition(record.topic(), record.partition()),
              new OffsetAndMetadata(record.offset() + 1, "no metadata"));

          consumer.commitAsync(offsets, null);


        }


      }
    } finally {
      consumer.close();
    }


  }

}
