package com.albrus.shiro.realms;

import com.albrus.common.utils.AlbrusConsts;
import com.albrus.shiro.entity.User;
import com.albrus.shiro.model.ResourceBO;
import com.albrus.shiro.service.IResourceService;
import com.albrus.shiro.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthRealm extends AuthorizingRealm {

    private final IUserService userService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    public AuthRealm(IUserService userService) {
        this.userService = userService;
    }

    // 登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        User user = userService.getByName(username);

        if (null == user) {
            return null;
        }

        // 放入shiro调用CredentialsMatcher校验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();
        List<ResourceBO> resources = resourceService.getActionsByUserId(user.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (ResourceBO resource : resources) {
            info.addRole(resource.getRoleName());
            if (null != resource.getPermission()) {
                info.addStringPermission(resource.getPermission());
            }
        }

        return info;
    }
}
