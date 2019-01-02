package com.albrus.shiro.controller;

import com.albrus.common.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController extends BaseController {

    @GetMapping(value= {"/", "/index"})
    public String index() {
        System.out.println("welcome to Albrus Account!");

        return "index";
    }

    @GetMapping(value= "login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        System.out.println("to login...");

        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();

        return "redirect:/login";
    }
}
