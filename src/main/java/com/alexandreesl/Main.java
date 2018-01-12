package com.alexandreesl;

import com.alexandreesl.producer.MyProducer;

public class Main {

  public static void main(String[] args) {

    MyProducer producer = new MyProducer();

    producer.sendMessage("test", "mysuperkey", "my value");


  }

}
