package com.sushmita.github.synchronization.static_var.solution_using_synchornized_block;

public class MyTask implements Runnable {
    private static int count;

    @Override
    public void run(){
        for(int i=0;i<2000;i++) {
            synchronized (MyTask.class) {
                increment();
            }
        }
    }

    private synchronized void increment(){
        count++;
    }

    public static int getCount(){
        return count;
    }
}
