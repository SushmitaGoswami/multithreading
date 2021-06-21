package com.sushmita.github.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futureList = new ArrayList();
        for(int i=0;i<4;i++) {
            futureList.add(executorService.submit(new MyTask(i)));
        }

        // print the return value
        for(int i=0;i<futureList.size();i++){
            Future<Integer> future = futureList.get(i);
            while(!future.isDone()){
                Thread.yield();
            }
            System.out.println(future.get());
        }
        executorService.shutdown();
    }
}
