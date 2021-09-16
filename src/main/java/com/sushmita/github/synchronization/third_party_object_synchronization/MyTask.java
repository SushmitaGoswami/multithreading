package com.sushmita.github.synchronization.third_party_object_synchronization;

public class MyTask implements Runnable {
    private SampleObject object;

    public MyTask(SampleObject object){
        this.object = object;
    }
    @Override
    public void run(){
        synchronized (object){
            object.incrA();
        }
    }
}
