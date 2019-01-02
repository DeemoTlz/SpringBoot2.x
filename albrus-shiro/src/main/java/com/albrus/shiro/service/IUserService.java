package com.albrus.shiro.service;

import com.albrus.shiro.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author albrus
 * @since 2019-01-02
 */
public interface IUserService extends IService<User> {

    User getByName(String name);

}
