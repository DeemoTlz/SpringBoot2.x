package com.albrus.consume.controller;


import com.albrus.common.controller.BaseController;
import com.albrus.common.model.Rtn;
import com.albrus.consume.service.IConsumeCostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("list")
	// @RequiresPermissions("shiro:consumeCost:view")
	public Rtn list() {
		return super.success(service.list());
	}

}
