package com.sushmita.github.synchronization.non_thread_safe;

public class SampleObject{
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void incrA(){
        int b = getA();
        b++;
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setA(b);
    }
}
