package com.albrus.shiro.service.impl;

import com.albrus.shiro.entity.AlbrusUser;
import com.albrus.shiro.mapper.AlbrusUserMapper;
import com.albrus.shiro.service.IAlbrusUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author albrus
 * @since 2018-12-07
 */
@Service
public class AlbrusUserServiceImpl extends ServiceImpl<AlbrusUserMapper, AlbrusUser> implements IAlbrusUserService {

}
