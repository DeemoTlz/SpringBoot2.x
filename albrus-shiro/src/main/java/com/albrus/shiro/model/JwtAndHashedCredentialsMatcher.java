package com.albrus.shiro.model;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class JwtAndHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        // 若 token 是 JWTToken 类，则已经校验过 token 的正确性，直接放过
        if (token instanceof JWTToken) {
            return true;
        }

        return super.doCredentialsMatch(token, info);
    }
}
