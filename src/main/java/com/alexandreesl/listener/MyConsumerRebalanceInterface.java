package com.alexandreesl.listener;

import java.util.Collection;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

public class MyConsumerRebalanceInterface implements ConsumerRebalanceListener {

  @Override
  public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
    System.out.println("I am losing the following partitions:");
    for (TopicPartition partition : partitions) {
      System.out.println(partition.partition());
    }
  }

  @Override
  public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
    System.out.println("I am starting on the following partitions:");
    for (TopicPartition partition : partitions) {
      System.out.println(partition.partition());
    }
  }
}
