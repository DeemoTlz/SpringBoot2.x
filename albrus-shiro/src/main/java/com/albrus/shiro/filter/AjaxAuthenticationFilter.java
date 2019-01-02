package com.albrus.shiro.filter;

import com.albrus.common.model.ErrorRtn;
import com.albrus.common.model.SuccessRtn;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        if (isAjax(request)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSONObject.toJSONString(new SuccessRtn()));

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
                } else if (e instanceof DisabledAccountException ) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(604);
                    out.println(JSONObject.toJSONString(new ErrorRtn(604, "账号禁用")));
                } else if (e instanceof ExpiredCredentialsException ) {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(605);
                    out.println(JSONObject.toJSONString(new ErrorRtn(605, "账号过期")));
                } else {
                    ((WebStatFilter.StatHttpServletResponseWrapper) response).setStatus(606);
                    out.println(JSONObject.toJSONString(new ErrorRtn(606, "登录失败")));
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
