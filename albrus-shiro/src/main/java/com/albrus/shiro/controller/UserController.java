package com.albrus.shiro.controller;


import com.albrus.common.model.Rtn;
import com.albrus.shiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.albrus.common.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author albrus
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/shiro/user")
public class UserController extends BaseController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public Rtn index() {
        System.out.println("welcome!");

        return super.success("123", service.list());
    }

}
