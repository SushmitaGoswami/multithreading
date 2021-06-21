package com.sushmita.github.synchronization.third_party_object_synchronization;

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
