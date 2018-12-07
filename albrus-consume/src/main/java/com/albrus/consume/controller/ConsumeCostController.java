package com.albrus.consume.controller;


import com.albrus.common.model.Rtn;
import com.albrus.consume.service.IConsumeCostService;
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
 * @since 2018-12-07
 */
@RestController
@RequestMapping("/consumeCost")
public class ConsumeCostController extends BaseController {

    private final IConsumeCostService service;

    @Autowired
    public ConsumeCostController(IConsumeCostService service) {
        this.service = service;
    }

    @GetMapping(value= {"/cost"})
    public Rtn index() {
        System.out.println("welcome!");

        return super.success("", service.list());
    }
}
