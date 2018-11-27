package com.albrus.shiro.controller;

import com.albrus.shiro.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping(value= {"/", "/index"})
    public String index() {
        System.out.println("welcome!");

        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam("user") String user, @RequestParam("password") String password) {
        System.out.println("user:" + user + ", password:" + password);

        return "{\"code_\": 0}";
    }

    @PostMapping(value = "/login2")
    public String login(@RequestBody User user) {
        System.out.println("user: " + user);

        return "{\"code_\": 0}";
    }
}
