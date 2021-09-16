package com.sushmita.github.message_queue_example.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> messageQueue = new ArrayBlockingQueue<Integer>(1000);

        Thread producer = new Thread(new Producer(messageQueue));
        Thread consumer = new Thread(new Consumer(messageQueue));

        producer.start();

        Thread.sleep(1000);

        consumer.start();
        
    }
}
