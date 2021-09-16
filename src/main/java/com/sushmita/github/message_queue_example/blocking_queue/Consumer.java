package com.sushmita.github.message_queue_example.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    public BlockingQueue messageQueue;

    public Consumer(BlockingQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            try {
                System.out.println("Consumed = " + messageQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
