package com.alexandreesl;

import com.alexandreesl.consumer.MyConsumer;
import com.alexandreesl.producer.MyProducer;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Please select operation (1 for producer, 2 for consumer) :");

    String operation = scanner.next();

    System.out.println("Please enter topic name :");

    String topic = scanner.next();

    if (operation.equals("1")) {

      MyProducer producer = new MyProducer();

      System.out.println("Please enter key :");

      String key = scanner.next();

      System.out.println("Please enter value :");

      String value = scanner.next();

      producer.sendMessage(topic, key, value);
    } else if (operation.equals("2")) {

      MyConsumer consumer = new MyConsumer();

      consumer.consume(topic);


    }


  }

}
