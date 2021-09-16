package com.sushmita.github.synchronization.deadlock.problem_simulation;


public class Main {
    public static void main(String[] args) {
        Object book = new Object();
        Object pen = new Object();

        Thread thread1 = new Thread(new MyTask1(book, pen));
        Thread thread2 = new Thread(new MyTask2(book, pen));

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
