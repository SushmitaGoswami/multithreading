package com.sushmita.github.fork_join;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7,6,8,9};
        Integer item = 6;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int result = pool.invoke(new SampleTask<Integer>(arr, 0, arr.length-1,item));
        System.out.println("Item= "+item + " found " + result + " times");
    }
}
