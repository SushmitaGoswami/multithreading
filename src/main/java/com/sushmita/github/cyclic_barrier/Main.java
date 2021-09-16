package com.sushmita.github.cyclic_barrier;

import com.sushmita.github.count_down_latch.Server;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Thread thread1 = new Thread(new SampleTask(cyclicBarrier));
        Thread thread2 = new Thread(new SampleTask(cyclicBarrier));
        Thread thread3 = new Thread(new SampleTask(cyclicBarrier));

        thread1.start();
        Thread.sleep(2000);
        thread2.start();
        Thread.sleep(2000);
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
