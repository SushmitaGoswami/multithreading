package com.sushmita.github.priority_blocking_queue;

public class SampleObject implements Comparable<SampleObject>{
    private String name;
    private int rank;

    public SampleObject(String name, int rank){
        this.name = name;
        this.rank = rank;
    }

    @Override
    public int compareTo(SampleObject o) {
        return this.rank - o.rank;
    }

    @Override
    public String toString() {
        return "SampleObject{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
