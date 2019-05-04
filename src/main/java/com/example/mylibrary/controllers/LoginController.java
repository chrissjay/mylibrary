package com.example.mylibrary.controllers;

import com.example.mylibrary.biz.LoginBiz;
import com.example.mylibrary.model.User;
import com.example.mylibrary.service.UserService;
import com.example.mylibrary.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public class LoginController {

    @Autowired
    private LoginBiz loginBiz;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
    public String register() {
        return "login/register";
    }

    @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
    public String doRegister(
            Model model,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            String t = loginBiz.register(user);
            CookieUtils.writeCookie("t", t, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login/login";
    }

    @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
    public String doLogin(
            Model model,
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            String t = loginBiz.login(email, password);
            CookieUtils.writeCookie("t", t, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
    public String doLogout(
            @CookieValue("t") String t
    ) {

        loginBiz.logout(t);
        return "redirect:/index";

    }
}
