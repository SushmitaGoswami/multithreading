package com.sushmita.github.threadlocal;

public class Framework {

    private API api;
    private UserContext context;

    public Framework(){
        context = new UserContext();
        api = new API();
    }

    public void delegate(String request){
        this.preProcess(request);
        api.doProcess(request);
        this.postProcess(request);
    }

    private void preProcess(String request){
        context.setUser(this.getUser(request));
    }

    private void postProcess(String request){
        context.removeUser();
    }
    private String getUser(String request){
        // :-P
        return request;
    }
}
