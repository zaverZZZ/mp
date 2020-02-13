package com.zaver.mp.utils.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        Map<String,Filter> filters = new HashMap<>();
        filters.put("shiro",new ShiroFilter());
        shiroFilter.setFilters(filters);
        // 不使用自定义filter时，设置下满两个地址，指定未登录和无权限时跳转的页面路径
         shiroFilter.setLoginUrl("/sys/unLogin");
        // shiroFilter.setUnauthorizedUrl("/");

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/sys/login","anon");
        filterMap.put("/sys/register","anon");
        filterMap.put("/statics/**", "anon");
         filterMap.put("/**", "authc"); // 拦截
//         filterMap.put("/**", "anon");    // 取消拦截，对所有接口免登录
//        filterMap.put("/**", "security");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     *创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
}
