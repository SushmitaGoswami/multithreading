package com.sushmita.github.threadlocal;

public class UserContext {
    private ThreadLocal<String> threadLocal;

    public UserContext(){
        threadLocal = new ThreadLocal<String>();
    }

    public void setUser(String userName){
        this.threadLocal.set(userName);
    }

    public void removeUser(){
        this.threadLocal.remove();
    }
}
