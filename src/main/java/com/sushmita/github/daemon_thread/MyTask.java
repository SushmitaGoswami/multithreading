package com.sushmita.github.daemon_thread;

public class MyTask implements Runnable {

    public void run() {
        while(!Thread.interrupted()){
            System.out.println("hey! task is running");
        }
        System.out.println("hey! task is interrupted! hence terminating...");
    }
}
