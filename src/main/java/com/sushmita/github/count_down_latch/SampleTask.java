package com.sushmita.github.count_down_latch;

import java.util.concurrent.CountDownLatch;

public class SampleTask implements Runnable{
    private CountDownLatch countDownLatch;

    public SampleTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run(){
        countDownLatch.countDown();
        System.out.println("Service has started!!");
    }
}
