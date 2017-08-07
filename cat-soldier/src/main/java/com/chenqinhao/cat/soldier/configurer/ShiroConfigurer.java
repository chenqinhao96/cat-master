package com.chenqinhao.cat.soldier.configurer;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@Configuration
public class ShiroConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("maoyu.cookie.name")
    private String cookieName;

    @Value("maoyu.cookie.max-age")
    private Integer maxAge;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/403"); // 未授权
        shiroFilterFactoryBean.setSuccessUrl("/index"); // 成功后
        shiroFilterFactoryBean.setLoginUrl("/login"); // 登录
        shiroFilterFactoryBean.setFilters(null); //

        Map filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilterChainDefinitions(null);
        logger.debug("ShiroFilterFactoryBean is loaded...");
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SecurityManager securityManagerBean(
            SubjectFactory factory,
            SubjectDAO subjectDAO,
            SessionManager sessionManager,
            CacheManager cacheManager,
            RememberMeManager rememberMeManager) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setSubjectFactory(factory);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealms(null);
        return securityManager;
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManagerBean(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        return sessionManager;
    }

    @Bean(name = "shiroSessionDAO")
    public SessionDAO shiroSessionDAO() {
        return null;
    }


    public Cookie rememeberMeCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName(cookieName);
        cookie.setMaxAge(maxAge);
        return cookie;
    }


    @Bean
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SessionManager sessionManager(CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookie(rememeberMeCookie());
        sessionManager.setSessionDAO(shiroSessionDAO());
        sessionManager.setCacheManager(cacheManager);
        return sessionManager;
    }


    @Bean(name = "rememberMeManager")
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememeberMeCookie());
        rememberMeManager.setCipherKey(Base64.decode("bWFveXUtY2hhbg=="));
        return rememberMeManager;
    }

    @Bean(name = "subjectDAO")
    public SubjectDAO subjectDAO() {
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        return subjectDAO;
    }

    @Bean(name = "subjectFactory")
    public SubjectFactory subjectFactory() {
        DefaultSubjectFactory subjectFactory = new DefaultSubjectFactory();
        return subjectFactory;
    }


    @Bean
    public Realm realm() {
        return null;
    }


}
