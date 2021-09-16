package com.sushmita.github.synchronization.special_case_study.solution_synchronized_block;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(2,3,4,5));
        Integer obj = 1;
        Thread thread1 = new Thread(new MyTask(list, obj));
        Thread thread2 = new Thread(new MyTask(list, obj));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list);
    }
}
