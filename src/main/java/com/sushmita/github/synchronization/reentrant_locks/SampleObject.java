package com.sushmita.github.synchronization.reentrant_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SampleObject{
    private int a;
    private ReadWriteLock lock;

    public SampleObject(){
        lock = new ReentrantReadWriteLock();
    }
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void incrA(){
        // get lock
        Lock lock = this.lock.writeLock();
        lock.lock();
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

            lock.unlock();
        }
    }
}
