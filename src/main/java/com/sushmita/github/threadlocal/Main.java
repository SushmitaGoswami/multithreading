package com.sushmita.github.threadlocal;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String request1 = "Hello";
        String request2 = "How are you?";
        String request3 = "Stay safe!";
        String request4 = "Bye";

        Server server = new Server();
        server.doServe(request1);
        server.doServe(request2);
        server.doServe(request3);
        server.doServe(request4);

        server.shutDown();
    }
}
