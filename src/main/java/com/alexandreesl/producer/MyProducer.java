package com.alexandreesl.producer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer {


  private KafkaProducer producer;


  public MyProducer() throws UnknownHostException {

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
