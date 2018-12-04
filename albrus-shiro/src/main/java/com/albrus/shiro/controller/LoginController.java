package com.albrus.shiro.controller;

import com.albrus.common.BaseController;
import com.albrus.common.model.Rtn;
import com.albrus.shiro.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController extends BaseController {

    @GetMapping(value= {"/", "/index"})
    public String index() {
        System.out.println("welcome!");

        return "index";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public Rtn login(@RequestBody User user) {
        System.out.println("user: " + user);

        return super.success();
    }
}
