package com.sushmita.github.message_queue_example.custom_implementation;

public class Producer implements Runnable {
    public MessageQueue<Integer> messageQueue;

    public Producer(MessageQueue<Integer> messageQueue){
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        int i = 0;
        while(!Thread.interrupted()) {
            try {
                messageQueue.enqueue(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
