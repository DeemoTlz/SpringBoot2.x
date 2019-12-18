package com.albrus.consume.controller;


import com.albrus.consume.entity.ConsumeCost;
import com.albrus.consume.service.IConsumeCostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.albrus.common.controller.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author albrus
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/consume/consumeCost")
public class ConsumeCostController extends BaseController {

	private final IConsumeCostService service;

	public ConsumeCostController(IConsumeCostService service) {
		this.service = service;
	}

	@GetMapping
	// @RequiresPermissions("shiro:consumeCost:view")
	public List<ConsumeCost> list() {
		return service.list();
	}

}
