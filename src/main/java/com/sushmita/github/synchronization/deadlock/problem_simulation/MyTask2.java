package com.sushmita.github.synchronization.deadlock.problem_simulation;

public class MyTask2 implements Runnable{

    private Object book;
    private Object pen;

    public MyTask2(Object book, Object pen){
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run(){
        synchronized (pen){
            try {
                System.out.println(Thread.currentThread().getName() + " has acquired a lock on pen and will sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (book){
                System.out.println(Thread.currentThread().getName() + " has acquired a lock on book");
                System.out.println(Thread.currentThread().getName() + " is writing...");
            }
        }
    }
}
