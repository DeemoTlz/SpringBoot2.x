package com.albrus.shiro.model;

import org.apache.shiro.authc.UsernamePasswordToken;

public class JWTToken extends UsernamePasswordToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String username, String password, boolean rememberMe, String host, String token) {
        super(username, password, rememberMe, host);
        this.token = token;
    }
}
