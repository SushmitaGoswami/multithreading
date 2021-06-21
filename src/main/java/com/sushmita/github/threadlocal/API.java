package com.sushmita.github.threadlocal;

public class API {
    private Service service;

    public API(){
        service = new Service();
    }

    public void doProcess(String request){
        service.doService(request);
    }
}
