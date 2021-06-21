package com.sushmita.github.message_queue_example.custom_implementation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue<Integer> messageQueue = new MessageQueue<Integer>(1000);
        Thread producer = new Thread(new Producer(messageQueue));
        Thread consumer = new Thread(new Consumer(messageQueue));

        producer.start();

        Thread.sleep(1000);

        consumer.start();
        
    }
}
