package com.albrus.shiro.service.impl;

import com.albrus.shiro.entity.Role;
import com.albrus.shiro.mapper.RoleMapper;
import com.albrus.shiro.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author albrus
 * @since 2018-12-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
