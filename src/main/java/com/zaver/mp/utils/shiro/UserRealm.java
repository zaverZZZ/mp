package com.zaver.mp.utils.shiro;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.rbac.model.RbacUser;
import com.zaver.mp.rbac.service.RbacUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName : UserRealm
 * @Description TODO
 * @Date : 2019/4/16 13:34
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RbacUserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        AuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断逻辑
        // 1 用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        token.getPassword();
        RbacUser rbacUser = userService.getOneByUserName(token.getUsername());
        if(rbacUser==null){
            // 用户名不存在
            throw new UnknownAccountException("账号不正确");
        }
        // 用户禁用状态
        if(rbacUser.getStatus()==0){
            throw new LockedAccountException("帐号已禁止登录，联系管理员");
        }
        // 2 判断密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(rbacUser, rbacUser.getPassword(), ByteSource.Util.bytes(rbacUser.getSalt()), getName());
        return info;
    }

    /**
     * 将密码的加密方式告知shiro
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
