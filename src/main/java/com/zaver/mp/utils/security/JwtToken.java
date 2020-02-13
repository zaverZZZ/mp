package com.zaver.mp.utils.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName : JwtToken
 * @Description TODO
 * @Date : 2020/2/13 1:19
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
