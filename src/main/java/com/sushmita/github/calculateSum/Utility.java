package com.sushmita.github.calculateSum;

public class Utility<T extends Number> {

    public double getSum(int startIndex, int endIndex, T[] arr){
        double sum = 0;
        for(int i = startIndex; i<endIndex; i++){
            sum += arr[i].doubleValue();
        }
        return sum;
    }
}
