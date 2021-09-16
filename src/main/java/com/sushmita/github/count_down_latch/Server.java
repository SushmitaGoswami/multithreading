package com.sushmita.github.count_down_latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    public void startServer() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(4);
        List<Future<?>> futureList = new ArrayList<>();
        for(int i=0;i<4;i++){
            futureList.add(executorService.submit(new SampleTask(countDownLatch)));
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println("Server has started!");

    }
}
