package com.albrus.shiro.model;

import org.apache.shiro.web.servlet.SimpleCookie;

public class JWTTokenCookie extends SimpleCookie {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public JWTTokenCookie() {
        this.setName(AUTHORIZATION_HEADER);
        this.setValue(null);
        this.setComment(null); // Cookie的用处说明
        this.setDomain(null); // 可以访问此cookie的域名
        this.setPath("/"); // 可以访问此cookie的页面路径
        this.setMaxAge(-1); // 过期时间
        this.setVersion(1); // 使用的版本号。0表示遵循Netscape的Cookie规范，1表示遵循W3C的RFC 2109规范
        this.setSecure(false); // 是否只能通过https来传递此条cookie
        this.setHttpOnly(true); // 只有在http请求头中会带有此cookie的信息
    }

    public JWTTokenCookie(String value) {
        this.setName(AUTHORIZATION_HEADER);
        this.setValue(value);
        this.setComment(null); // Cookie的用处说明
        this.setDomain(null); // 可以访问此cookie的域名
        this.setPath("/"); // 可以访问此cookie的页面路径
        this.setMaxAge(-1); // 过期时间
        this.setVersion(1); // 使用的版本号。0表示遵循Netscape的Cookie规范，1表示遵循W3C的RFC 2109规范
        this.setSecure(false); // 是否只能通过https来传递此条cookie
        this.setHttpOnly(true); // 只有在http请求头中会带有此cookie的信息
    }

    public static String getAuthorizationHeader() {
        return AUTHORIZATION_HEADER;
    }
}
