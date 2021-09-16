package com.sushmita.github.fork_join;

import java.util.concurrent.RecursiveTask;

public class SampleTask<T> extends RecursiveTask<Integer> {
    private T arr[];
    private int startIndex;
    private int endIndex;
    private T searchElement;

    public SampleTask(T arr[], int startIndex, int endIndex, T searchElement){
        this.arr = arr;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        System.out.println("Thread= "+Thread.currentThread().getName());
        int size = (endIndex - startIndex) + 1;
        if(size>3){
            int mid = (endIndex - startIndex)/2 + startIndex;
            SampleTask<T> sampleTask1 = new SampleTask<>(arr, startIndex, mid, searchElement);
            SampleTask<T> sampleTask2 = new SampleTask<>(arr, mid+1, endIndex, searchElement);

            sampleTask1.fork();
            sampleTask2.fork();

            return sampleTask1.join() + sampleTask2.join();
        }
        else {
            return getCount();
        }
    }

    private Integer getCount(){
        int count = 0;
        for(int index=startIndex;index<=endIndex;index++){
            if(arr[index] == searchElement){
                count++;
            }
        }
        return count;
    }

}
