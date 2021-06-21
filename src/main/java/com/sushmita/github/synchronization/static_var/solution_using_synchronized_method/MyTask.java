package com.sushmita.github.synchronization.static_var.solution_using_synchronized_method;

public class MyTask implements Runnable {
    private static int count;

    @Override
    public void run(){
        for(int i=0;i<2000;i++) {
            increment();
        }
    }

    private synchronized static void increment(){
        count++;
    }

    public static int getCount(){
        return count;
    }
}
