package com.zaver.mp.utils.security;

import com.zaver.mp.utils.Constants;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName : ShiroConfig
 * @Description TODO
 * @Date : 2019/4/14 22:14
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */

@Configuration
public class ShiroConfig {

    /**
     *创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        // 设置安全管理器
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 设置自定义filter
        Map<String,Filter> filters = new HashMap<>();
        filters.put("shiro",new ShiroFilter());
        shiroFilter.setFilters(filters);
        // 设置下面两个地址，指定未登录和无权限时跳转的路径
        shiroFilter.setLoginUrl("/sys/rbac/unLogin");
        shiroFilter.setUnauthorizedUrl("/sys/rbac/notPermit");
        // 前后端未分离项目中，登录成功后的跳转路径
        //shiroFilter.setSuccessUrl("/");
        // 过滤器链map 必须是LinkedHashMap
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/sys/rbac/logout","logout");
        filterMap.put("/sys/rbac/login","anon");
        filterMap.put("/sys/rbac/register","anon");
        filterMap.put("/statics/**", "anon");
//        filterMap.put("/**", "authc"); // 拦截
        filterMap.put("/**", "anon");    // 取消拦截，对所有接口免登录
//        filterMap.put("/**", "security");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     *SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager  getSecurityManager(UserRealm userRealm,
                                                         ShiroSessionManager sessionManager,
                                                         RedisCacheManager redisCacheManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 必须要这这种方式 设置,直接new SessionManager 不行
        // 如果不是前后端分离, 就不用设置设置这个,也不用配置Bean
        securityManager.setSessionManager(sessionManager);
        // 使用自定义的cachaManager
        securityManager.setCacheManager(redisCacheManager);
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 自定义sessionManager  配置session持久化
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO, ShiroSessionManager shiroSessionManager){
        // 自定义customSessionManager
        // 配置session持久化
        shiroSessionManager.setSessionDAO(redisSessionDAO);
        // 超时时间,默认30分钟,会话超时.单位毫秒
        shiroSessionManager.setGlobalSessionTimeout(10*60*1000);
//        Cookie cookie = new SimpleCookie(Constants.REQUEST_HEADER_AUTHIRIZATION);
        Cookie cookie = new SimpleCookie("token");
        shiroSessionManager.setSessionIdCookie(cookie);
        shiroSessionManager.setSessionIdCookieEnabled(true);
        shiroSessionManager.setSessionIdUrlRewritingEnabled(false);
        return shiroSessionManager;
    }

    /**
     * 配置redisManager
     */
    @Bean
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        redisManager.setDatabase(2);
        return redisManager;
    }

    /**
     * 配置具体cache实现类RedisCacheManager
     * 为什么要使用缓存:
     * 缓存组件位于SecurityManager中,在UserRealm数据域中,由于授权方法中每次都要查询数据库,性能受影响,因此将数据缓存起来,提高查询效率
     * 除了使用Redis缓存,还能使用shiro-ehcache
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        // 设置过期时间,单位秒
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

    /**
     * 自定义session持久化
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager, ShiroSessionIdGenerator sessionIdGenerator) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        // 配置自定义sessionId,shiro自动生成sessionId不满足条件时可以使用
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return redisSessionDAO;
    }

    /**
     * 管理shiro一些bean的生命周期,即bean初始化与销毁
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 作加入shiro注解的使用，不加入这个AOP注解不生效(shiro的注解 例如 @RequiresGuest)
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor AASA = new AuthorizationAttributeSourceAdvisor();
        AASA.setSecurityManager(securityManager);
        return AASA;
    }

    /**
     * 作用: 用来扫描上下文寻找所有的Advistor(通知器),
     * 将符合条件的Advisor应用到切入点的Bean中，
     * 需要在LifecycleBeanPostProcessor创建后才可以创建
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator DAAPC = new DefaultAdvisorAutoProxyCreator();
        DAAPC.setUsePrefix(true);
        return DAAPC;
    }
}
