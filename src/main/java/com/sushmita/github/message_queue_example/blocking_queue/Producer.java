package com.sushmita.github.message_queue_example.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    public BlockingQueue messageQueue;

    public Producer(BlockingQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        int i = 0;
        while(!Thread.interrupted()) {
            try {
                System.out.println("Produced = " + i);
                messageQueue.put(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
