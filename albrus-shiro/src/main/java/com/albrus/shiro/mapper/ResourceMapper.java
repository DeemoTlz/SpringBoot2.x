package com.albrus.shiro.mapper;

import com.albrus.shiro.entity.Resource;
import com.albrus.shiro.model.MenuBO;
import com.albrus.shiro.model.ResourceBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author albrus
 * @since 2019-01-21
 */

@Component(value = "resourceMapper")
public interface ResourceMapper extends BaseMapper<Resource> {

    List<ResourceBO> getActionsByUserId(Long userId);

    List<MenuBO> getContentsByUserId(Long userId);

}
