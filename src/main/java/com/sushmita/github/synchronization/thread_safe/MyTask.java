package com.sushmita.github.synchronization.thread_safe;

public class MyTask implements Runnable {
    private SampleObject object;

    public MyTask(SampleObject object){
        this.object = object;
    }
    @Override
    public void run(){
        object.incrA();
    }
}
