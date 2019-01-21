package com.albrus.shiro.controller;


import com.albrus.shiro.entity.Resource;
import com.albrus.shiro.service.IResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.albrus.common.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author albrus
 * @since 2019-01-21
 */
@RestController
@RequestMapping("/shiro/resource")
public class ResourceController extends BaseController {

    private final
    IResourceService service;

    @Autowired
    public ResourceController(IResourceService service) {
        this.service = service;
    }

    @GetMapping
    @RequiresPermissions("shiro:resource:view")
    public List<Resource> list() {

        return service.list();
    }

}
