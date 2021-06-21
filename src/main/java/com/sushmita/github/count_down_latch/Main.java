package com.sushmita.github.count_down_latch;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
