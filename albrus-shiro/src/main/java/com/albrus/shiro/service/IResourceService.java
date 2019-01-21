package com.albrus.shiro.service;

import com.albrus.shiro.entity.Resource;
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

   List<Resource> getResourceByUserId(Long userId);
}
