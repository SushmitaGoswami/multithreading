package com.sushmita.github.non_blocking_lock_atomic_operation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Connection connection = new Connection();
        Thread thread1 = new Thread(connection);
        Thread thread2 = new Thread(connection);
        Thread thread3 = new Thread(connection);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
