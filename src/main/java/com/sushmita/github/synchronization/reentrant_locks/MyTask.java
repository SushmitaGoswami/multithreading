package com.sushmita.github.synchronization.reentrant_locks;

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
