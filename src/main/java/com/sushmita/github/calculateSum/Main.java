package com.sushmita.github.calculateSum;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int n = 10000;
        Integer[] arr = new Integer[n];
        for(int i = 0;i<n; i++){
            arr[i] = (i+1);
        }
        int step = 100;
        int no_Of_Thread = (n/step);
        ExecutorService service = Executors.newFixedThreadPool(no_Of_Thread);
        List<Future<Double>> list = new ArrayList<Future<Double>>();

        long startTime = System.currentTimeMillis();
        int stepIndex = 0;
        for(int i=0;i<no_Of_Thread; i++){
            MyTask<Integer> myTask = new MyTask<Integer>(arr, stepIndex, stepIndex+step);
            stepIndex += step;
            list.add(service.submit(myTask));
        }
        double sum = 0;
        for(int i=0; i<no_Of_Thread; i++){
            while(!list.get(i).isDone()){
                Thread.yield();
            }
            System.out.println(list.get(i).get());
            sum += list.get(i).get();
        }

        service.shutdownNow();
        long endTime = System.currentTimeMillis();

        System.out.println("Sum= " + sum);
        System.out.println("Time Taken (in millis.)= "+ (endTime - startTime));
    }
}
