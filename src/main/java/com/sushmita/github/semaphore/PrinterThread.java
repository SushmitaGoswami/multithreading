package com.sushmita.github.semaphore;

import java.util.concurrent.Semaphore;

public class PrinterThread implements Runnable{
    private Semaphore semaphore;
    private int id;

    public PrinterThread(Semaphore semaphore, int id){
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run(){
        try {
            semaphore.acquire();
            System.out.println("Printer is running = "+id);
            Thread.sleep(2000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
