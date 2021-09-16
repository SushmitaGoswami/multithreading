package com.sushmita.github.synchronization.reentrant_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SampleObject{
    private final Lock writeLock;
    private int a;
    private ReadWriteLock lock;


    public SampleObject(){
        lock = new ReentrantReadWriteLock();
        writeLock = this.lock.writeLock();
    }
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void incrA(){
        // get lock
        //Lock lock = this.lock.writeLock();
        writeLock.lock();
        try {
            int b = getA();
            b++;
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setA(b);
        }
        finally {
            //release lock

            writeLock.unlock();
        }
    }
}
