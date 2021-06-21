package com.sushmita.github.threadgroup;


public class Main {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();

        // Create a separate thread group
        ThreadGroup threadGroup = new ThreadGroup("my_thread_group");
        threadGroup.setMaxPriority(7);

        // ASSOCIATING A THREAD WITH THREAD GROUP
        Thread thread = new Thread(threadGroup, myTask,"my_thread");
        thread.setPriority(8);
        thread.start();

        System.out.println("System Threads are as follows...");
        ThreadGroup systemThreadGroup = Thread.currentThread().getThreadGroup();
        while(systemThreadGroup.getParent()!=null){
            systemThreadGroup = systemThreadGroup.getParent();
        }

        systemThreadGroup.list();
    }

}
