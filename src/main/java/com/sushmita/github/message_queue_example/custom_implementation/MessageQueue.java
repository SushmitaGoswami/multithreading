package com.sushmita.github.message_queue_example.custom_implementation;

import java.util.ArrayList;
import java.util.List;

public class MessageQueue<T> {
    private List<T> list;
    private int limit;

    public MessageQueue(int limit){
        list = new ArrayList<T>();
        this.limit = limit;
    }

    public synchronized void enqueue(T obj) throws InterruptedException {
     while(isFull()){
         System.out.println("List is full! will wait... ");
         wait();
     }
     list.add(obj);
     System.out.println("Produced item = " + obj);
     this.notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while(isEmpty()){
            System.out.println("List is empty! will wait... ");
            wait();
        }
        T remove = list.remove(0);
        System.out.println("Consumed item = " + remove);
        this.notify();
        return remove;
    }

    private synchronized boolean isEmpty(){
        return list.isEmpty();
    }
    private synchronized boolean isFull(){
        return (this.limit <= this.list.size());
    }
}
