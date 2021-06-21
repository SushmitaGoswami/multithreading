package com.sushmita.github.callable;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Integer> {

    private int value;
    public MyTask(int v){
        value = v;
    }

    public Integer call() throws Exception {
        return value + 100;
    }
}
