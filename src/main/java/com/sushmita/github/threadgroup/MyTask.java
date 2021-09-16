package com.sushmita.github.threadgroup;

public class MyTask implements Runnable {

    public void run() {
//        while(!Thread.interrupted()){
//            System.out.println("hey! task is running");
//        }
//        System.out.println("hey! task is interrupted! hence terminating...");

        System.out.println("hey! task is running");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
