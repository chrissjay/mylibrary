package com.example.mylibrary.service;

import com.example.mylibrary.model.User;
import com.example.mylibrary.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser() {
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user) {
        ConcurrentUtils.setHost(user);
    }
}
