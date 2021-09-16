package com.sushmita.github.synchronization.static_var.problem;

public class Main {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new MyTask());
        Thread thread2 = new Thread(new MyTask());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(MyTask.getCount());
    }
}
