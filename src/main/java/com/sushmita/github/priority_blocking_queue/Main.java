package com.sushmita.github.priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<SampleObject> priorityBlockingQueue = new PriorityBlockingQueue<SampleObject>();

        priorityBlockingQueue.add(new SampleObject("test1", 3));
        priorityBlockingQueue.add(new SampleObject("test2", 2));
        priorityBlockingQueue.add(new SampleObject("test3", 1));

        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());



    }
}
