package com.albrus.shiro.filter;

import com.albrus.common.model.ErrorRtn;
import com.albrus.common.model.SuccessRtn;
import com.albrus.common.utils.JWTUtil;
import com.albrus.shiro.entity.User;
import com.albrus.shiro.model.JWTToken;
import com.albrus.shiro.model.JWTTokenCookie;
import com.albrus.shiro.service.IUserService;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        //判断请求的请求头是否带上 "Authorization"
        String jwtToken = this.getAuthCookie(request);
        if (null != jwtToken) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                return this.checkJwtToken(request, jwtToken);
            } catch (AuthenticationException e) {
                new JWTTokenCookie().removeFrom(WebUtils.toHttp(request), WebUtils.toHttp(response));
                onLoginFailure(null, e, request, response);
                return false;
            }
        } else {
            if (SecurityUtils.getSubject().isAuthenticated()) {
                SecurityUtils.getSubject().logout();
            }
        }

        return !this.isLoginRequest(request, response) && this.isPermissive(mappedValue);
    }

    private boolean checkJwtToken(ServletRequest request, String jwtToken) {

        String username = JWTUtil.getUsername(jwtToken);
        if (null == username) {
            throw new UnsupportedTokenException();
        }

        ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        if (null == ctx) {
            return false;
        }
        IUserService userService = ctx.getBean(IUserService.class);

        User user = userService.getByName(username);
        if (null == user) {
            throw new UnknownAccountException();
        }

        if (!JWTUtil.verify(jwtToken, username, user.getPassword())) {
            if (SecurityUtils.getSubject().isAuthenticated()) {
                SecurityUtils.getSubject().logout();
            }
            throw new IncorrectCredentialsException();
        }

        if (!SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().login(new JWTToken(username, "", false, request.getRemoteHost(), jwtToken));
        }

        return true;
    }

    private String getAuthHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(JWTTokenCookie.getAuthorizationHeader());
    }

    private String getAuthCookie(ServletRequest request) {

        return new JWTTokenCookie().readValue(WebUtils.toHttp(request), null);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //String username = String.valueOf(token.getPrincipal());
        String password = ((User) subject.getPrincipal()).getPassword();

        String jwtToken = JWTUtil.sign(token.getPrincipal().toString(), password);

        Cookie cookie = new JWTTokenCookie(jwtToken);
        cookie.saveTo(WebUtils.toHttp(request), WebUtils.toHttp(response));

        if (isAjax(request)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSONObject.toJSONString(new SuccessRtn("", jwtToken)));

            out.flush();
            out.close();

            return false;
        }

        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (isAjax(request)) {
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();

                if (e instanceof UnknownAccountException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(601);
                    out.println(JSONObject.toJSONString(new ErrorRtn(601, "账号不存在")));
                } else if (e instanceof IncorrectCredentialsException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(602);
                    out.println(JSONObject.toJSONString(new ErrorRtn(602, "密码错误")));
                } else if (e instanceof ExcessiveAttemptsException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(603);
                    out.println(JSONObject.toJSONString(new ErrorRtn(603, "登录失败次数过多")));
                } else if (e instanceof DisabledAccountException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(604);
                    out.println(JSONObject.toJSONString(new ErrorRtn(604, "账号禁用")));
                } else if (e instanceof ExpiredCredentialsException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(605);
                    out.println(JSONObject.toJSONString(new ErrorRtn(605, "账号过期")));
                } else if (e instanceof UnsupportedTokenException) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(606);
                    out.println(JSONObject.toJSONString(new ErrorRtn(606, "Token无效")));
                } else {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(607);
                    out.println(JSONObject.toJSONString(new ErrorRtn(607, "登录失败")));
                }

                out.flush();
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return false;
        }

        return super.onLoginFailure(token, e, request, response);
    }

    private static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");

        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
