package com.sushmita.github.synchronization.deadlock.problem_simulation;

public class MyTask1 implements Runnable{

    private Object book;
    private Object pen;

    public MyTask1(Object book, Object pen){
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run(){
        synchronized (book){
            try {
                System.out.println(Thread.currentThread().getName() + " has acquired a lock on book and will sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (pen){
                System.out.println(Thread.currentThread().getName() + " has acquired a lock on pen");
                System.out.println(Thread.currentThread().getName() + " is writing...");
            }
        }
    }
}
