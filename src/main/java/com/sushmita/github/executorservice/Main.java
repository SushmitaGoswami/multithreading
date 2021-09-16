package com.sushmita.github.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<4;i++) {
            executorService.submit(new MyTask());
        }
        executorService.shutdown();
    }
}
