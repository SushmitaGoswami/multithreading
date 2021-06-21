package com.sushmita.github.synchronization.special_case_study.problems_with_synchronization_method;

import java.util.List;

public class Utility<T> {
    private List<T> list;

    public Utility(List<T> list){
        this.list = list;
    }
    public synchronized boolean contains(T obj){
        return list.contains(obj);
    }

    public synchronized void add(T obj){
        list.add(obj);
    }
}
