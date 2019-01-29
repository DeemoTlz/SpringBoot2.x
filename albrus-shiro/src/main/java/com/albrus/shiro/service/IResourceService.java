package com.albrus.shiro.service;

import com.albrus.shiro.entity.Resource;
import com.albrus.shiro.model.MenuBO;
import com.albrus.shiro.model.ResourceBO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author albrus
 * @since 2019-01-21
 */
public interface IResourceService extends IService<Resource> {

   List<ResourceBO> getActionsByUserId(Long userId);

   List<MenuBO> getContentsByUserId(Long userId);

}
