package com.albrus.shiro.service.impl;

import com.albrus.shiro.entity.User;
import com.albrus.shiro.mapper.UserMapper;
import com.albrus.shiro.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author albrus
 * @since 2019-01-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByName(String name) {
        User user = new User();
        user.setUsername(name);

        return this.getOne(new QueryWrapper<>(user));
    }

}
