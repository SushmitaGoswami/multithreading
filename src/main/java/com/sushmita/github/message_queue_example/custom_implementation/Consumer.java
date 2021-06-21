package com.sushmita.github.message_queue_example.custom_implementation;

public class Consumer implements Runnable {
    public MessageQueue<Integer> messageQueue;

    public Consumer(MessageQueue<Integer> messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            try {
                messageQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
