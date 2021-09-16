package com.sushmita.github.calculateSum;

import java.util.concurrent.Callable;

public class MyTask<T extends Number> implements Callable<Double>
{
    int startIndex;
    int endIndex;
    T[] arr;

    public MyTask(T[] arr, int startIndex, int endIndex){
        this.arr = arr;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public Double call() throws Exception {
        Utility<T> utility = new Utility<T>();
        return utility.getSum(startIndex, endIndex, arr);
    }
}
