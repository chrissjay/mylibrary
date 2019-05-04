package com.example.mylibrary.service;

import com.example.mylibrary.dao.UserDAO;
import com.example.mylibrary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    public User getUser(String email) {
        return userDAO.selectUserByEmail(email);
    }

    public User getUser(int id) {
        return userDAO.selectUserById(id);
    }

}
