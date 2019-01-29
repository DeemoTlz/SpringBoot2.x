package com.albrus.shiro.filter;

import com.albrus.common.model.ErrorRtn;
import com.albrus.common.model.SuccessRtn;
import com.albrus.common.utils.AlbrusConsts;
import com.albrus.common.utils.JWTUtil;
import com.albrus.shiro.entity.Resource;
import com.albrus.shiro.entity.User;
import com.albrus.shiro.model.JWTToken;
import com.albrus.shiro.model.JWTTokenCookie;
import com.albrus.shiro.model.MenuBO;
import com.albrus.shiro.service.IResourceService;
import com.albrus.shiro.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AjaxAuthenticationFilter extends FormAuthenticationFilter {

    private final IUserService userService;
    private final IResourceService resourceService;

    public AjaxAuthenticationFilter(IUserService userService, IResourceService resourceService) {
        this.userService = userService;
        this.resourceService = resourceService;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        // 判断请求的请求头是否带上 "Authorization"
        // String jwtToken = this.getAuthHeader(request);
        String jwtToken = this.getAuthCookie(request);
        if (null != jwtToken) {
            //如果存在，则进入 checkJwtToken 方法检查 token 是否正确
            try {
                User user = this.checkJwtToken(request, jwtToken);

                // 校验成功，更新token
                setAuthCookie(request, response, user);

                return true;
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

    /**
     *
     * @ClassName checkJwtToken 验证token是否有效
     *
     * @author qi_jiahu
     * @date 2019/1/21 10:31
     * @Param [request, jwtToken]
     * @return User 当前token对应的用户实体
     */
    private User checkJwtToken(ServletRequest request, String jwtToken) {

        String username = JWTUtil.getUsername(jwtToken);
        if (null == username) {
            throw new UnsupportedTokenException();
        }

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

        return user;
    }

    /**
     *
     * @ClassName getAuthHeader 从请求头中获取token字符串
     *
     * @author qi_jiahu
     * @date 2019/1/21 10:30
     * @Param [request]
     * @return java.lang.String token字符串
     */
    private String getAuthHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(JWTTokenCookie.getAuthorizationHeader());
    }

    /**
     *
     * @ClassName getAuthCookie 从cookie中获取token字符串
     *
     * @author qi_jiahu
     * @date 2019/1/21 10:30
     * @Param [request]
     * @return java.lang.String token字符串
     */
    private String getAuthCookie(ServletRequest request) {

        return new JWTTokenCookie().readValue(WebUtils.toHttp(request), null);
    }

    /**
     *
     * @ClassName setAuthCookie 设置JWT token到cookie中
     *
     * @author qi_jiahu
     * @date 2019/1/22 10:34
     * @Param [request, response, user]
     * @return java.lang.String JWT token
     */
    private String setAuthCookie(ServletRequest request, ServletResponse response, User user) {
        String jwtToken = JWTUtil.sign(user.getUsername(), user.getPassword());

        Cookie cookie = new JWTTokenCookie(jwtToken);
        cookie.saveTo(WebUtils.toHttp(request), WebUtils.toHttp(response));

        return jwtToken;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User user = (User) subject.getPrincipal();
        String jwtToken = setAuthCookie(request, response, user);

        // 获取该用户有哪些菜单
        List<MenuBO> contents = resourceService.getContentsByUserId(user.getId());
        List<MenuBO> menus = Lists.newLinkedList();

        for (MenuBO menu : contents) {
            // 遍历菜单，找出第一级父级菜单
            if (AlbrusConsts.RESOURCE_NO_PARENT == menu.getParentId()) {
                menus.add(menu);
                // url为空则说明还有子菜单
                if (StringUtils.isBlank(menu.getUrl())) {
                    menu.setChilds(this.getChilds(menu.getId().intValue(), contents));
                }
            }
        }

        if (isAjax(request)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSONObject.toJSONString(new SuccessRtn(jwtToken, menus)));

            out.flush();
            out.close();

            return false;
        }

        request.setAttribute("menu", menus);
        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     *
     * @ClassName getChilds 获取当前菜单的子菜单
     *
     * @author qi_jiahu
     * @date 2019/1/29 10:51
     * @Param [id, menus] id: 获取子菜单的菜单id，menus: 所有菜单集合
     * @return java.util.List<com.albrus.shiro.model.MenuBO>
     */
    private List<MenuBO> getChilds(Integer id, List<MenuBO> menus) {
        List<MenuBO> childs = Lists.newLinkedList();

        for (MenuBO menu : menus) {
            if (menu.getParentId() == id.intValue()) {
                childs.add(menu);
                // url为空则说明还有子菜单
                if (StringUtils.isBlank(menu.getUrl())) {
                    menu.setChilds(this.getChilds(menu.getId().intValue(), menus));
                }
            }
        }

        return childs;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (isAjax(request)) {
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();
                HttpServletResponse httpResponse = WebUtils.toHttp(response);
                ErrorRtn rtn;

                if (e instanceof UnknownAccountException) {
                    httpResponse.setStatus(601);
                    rtn = new ErrorRtn(601, "账号不存在");
                } else if (e instanceof IncorrectCredentialsException) {
                    httpResponse.setStatus(602);
                    rtn = new ErrorRtn(602, "密码错误");
                } else if (e instanceof ExcessiveAttemptsException) {
                    httpResponse.setStatus(603);
                    rtn = new ErrorRtn(603, "登录失败次数过多");
                } else if (e instanceof DisabledAccountException) {
                    httpResponse.setStatus(604);
                    rtn = new ErrorRtn(604, "账号禁用");
                } else if (e instanceof ExpiredCredentialsException) {
                    httpResponse.setStatus(605);
                    rtn = new ErrorRtn(605, "账号过期");
                } else if (e instanceof UnsupportedTokenException) {
                    httpResponse.setStatus(606);
                    rtn = new ErrorRtn(606, "Token无效");
                } else {
                    httpResponse.setStatus(607);
                    rtn = new ErrorRtn(607, "服务器一不小心被怪兽吃掉了Ծ‸Ծ");
                }

                out.println(JSONObject.toJSONString(rtn));
                out.flush();
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return false;
        }

        return super.onLoginFailure(token, e, request, response);
    }

    /**
     *
     * @ClassName isAjax 是否是ajax请求
     *
     * @author qi_jiahu
     * @date 2019/1/21 10:32
     * @Param [request]
     * @return boolean true: 是
     */
    private static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");

        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
