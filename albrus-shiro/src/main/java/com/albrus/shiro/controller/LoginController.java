package com.albrus.shiro.controller;

import com.albrus.common.controller.BaseController;
import com.albrus.shiro.model.JWTTokenCookie;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController extends BaseController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        System.out.println("welcome to Albrus Account!");

        return "index";
    }

    @GetMapping(value = "login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        System.out.println("to login...");

        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
        new JWTTokenCookie().removeFrom(request, response);

        return "redirect:/login";
    }
}
