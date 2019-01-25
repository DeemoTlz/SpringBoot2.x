package com.albrus.main.config;

import com.albrus.shiro.filter.AjaxAuthenticationFilter;
import com.albrus.shiro.model.JWTAndHashedCredentialsMatcher;
import com.albrus.shiro.realms.AuthRealm;
import com.albrus.shiro.service.IResourceService;
import com.albrus.shiro.service.IUserService;
import com.google.common.collect.Maps;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private final IUserService userService;

    private final IResourceService resourceService;

    @Autowired
    public ShiroConfig(IUserService userService, IResourceService resourceService) {
        this.userService = userService;
        this.resourceService = resourceService;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/**/favicon.ico", "anon");
        filterChainDefinitionMap.put("logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filter = Maps.newLinkedHashMap();
        filter.put("authc", new AjaxAuthenticationFilter(userService, resourceService));
        shiroFilterFactoryBean.setFilters(filter);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        // 记住我cookie生效时间,单位是秒
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);

        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(simpleCookie);
        // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        rememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));

        return rememberMeManager;
    }

    // 核心安全管理器
    @Bean
    public SecurityManager securityManager(AuthRealm authRealm, CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setRememberMeManager(rememberMeManager);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    // 登录、权限认证
    @Bean
    public AuthRealm authRealm(JWTAndHashedCredentialsMatcher credentialsMatcher) {
        AuthRealm authRealm = new AuthRealm(userService);
        authRealm.setCredentialsMatcher(credentialsMatcher);

        return authRealm;
    }

    @Bean
    public JWTAndHashedCredentialsMatcher credentialsMatcher() {
        JWTAndHashedCredentialsMatcher credentialsMatcher = new JWTAndHashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(1024);

        return credentialsMatcher;
    }

}
