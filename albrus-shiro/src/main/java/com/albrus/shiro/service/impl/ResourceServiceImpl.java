package com.albrus.shiro.service.impl;

import com.albrus.shiro.entity.Resource;
import com.albrus.shiro.mapper.ResourceMapper;
import com.albrus.shiro.model.ResourceBO;
import com.albrus.shiro.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author albrus
 * @since 2019-01-21
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private final
    ResourceMapper mapper;

    @Autowired
    public ResourceServiceImpl(ResourceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ResourceBO> getResourceByUserId(Long userId) {
        return mapper.getResourceByUserId(userId);
    }
}
