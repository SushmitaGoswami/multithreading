package com.sushmita.github.synchronization.third_party_object_synchronization;

public class Main {

    public static void main(String[] args) {
        SampleObject object = new SampleObject();
        Thread thread1 = new Thread(new MyTask(object));
        Thread thread2 = new Thread(new MyTask(object));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(object.getA());
    }
}
