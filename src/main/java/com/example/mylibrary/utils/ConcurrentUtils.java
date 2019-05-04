package com.example.mylibrary.utils;

import com.example.mylibrary.model.User;

public class ConcurrentUtils {

    private static ThreadLocal<User> host = new ThreadLocal<>();

    public static User getHost(){
        return host.get();
    }

    public static void setHost(User user){
        host.set(user);
    }
}
