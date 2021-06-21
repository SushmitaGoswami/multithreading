package com.sushmita.github.synchronization.special_case_study.solution_synchronized_block;

import java.util.List;

public class MyTask<T> implements Runnable{

    private List<T> list;
    private T obj;

    public MyTask(List<T> list, T obj){
        this.list = list;
        this.obj = obj;
    }

    @Override
    public void run() {
        Utility<T> utility = new Utility(this.list);
        synchronized (list) {
            if (!utility.contains(this.obj)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                utility.add(this.obj);
            }
        }
    }
}
