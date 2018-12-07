package com.albrus.shiro.controller;


import com.albrus.common.model.Rtn;
import com.albrus.shiro.service.IAlbrusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.albrus.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author albrus
 * @since 2018-12-07
 */
@RestController
@RequestMapping("/albrusUser")
public class AlbrusUserController extends BaseController {

    private final
    IAlbrusUserService service;

    @Autowired
    public AlbrusUserController(IAlbrusUserService service) {
        this.service = service;
    }

    @GetMapping(value= {"/user"})
    public Rtn index() {
        System.out.println("welcome!");

        return super.success("", service.list());
    }

}
