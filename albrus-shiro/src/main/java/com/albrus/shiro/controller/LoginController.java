package com.albrus.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam("user") String user, @RequestParam("password") String password) {
        System.out.println("user:" + user + ", password:" + password);

        return null;
    }
}
