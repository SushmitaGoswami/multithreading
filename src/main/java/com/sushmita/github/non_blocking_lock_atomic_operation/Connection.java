package com.sushmita.github.non_blocking_lock_atomic_operation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Connection implements Runnable {

    private AtomicBoolean initialized;

    public Connection(){
        initialized = new AtomicBoolean(false);
    }

    public void run(){
        if(initialized.get()){
            System.out.println("Already initialized");
        }
        boolean success = initialized.compareAndSet(false, true);
        if(success){
            System.out.println("Initialization done");
        }
    }
}
