package com.sushmita.github.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private ExecutorService service;
    private List<Future> futureList;
    private Framework framework;

    public Server(){
        service = Executors.newFixedThreadPool(1000);
        futureList = new ArrayList<Future>();
        framework = new Framework();
    }

    public void doServe(final String request) throws ExecutionException, InterruptedException {

        Future<?> submit = service.submit(new Runnable() {
            @Override
            public void run() {
                framework.delegate(request);
            }
        });
        while(!submit.isDone()){
            Thread.yield();
        }
        submit.get();
    }

    public void shutDown(){
        for(Future<?> future: futureList) {
            while(!future.isDone()){
                Thread.yield();
            }
        }
        service.shutdownNow();
    }
}
