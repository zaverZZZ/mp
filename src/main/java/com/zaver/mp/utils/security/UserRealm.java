package com.zaver.mp.utils.security;

import com.zaver.mp.sys.rbac.model.RbacRole;
import com.zaver.mp.sys.rbac.model.RbacUser;
import com.zaver.mp.sys.rbac.service.RbacRoleService;
import com.zaver.mp.sys.rbac.service.RbacUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : UserRealm
 * @Description TODO
 * @Date : 2019/4/16 13:34
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RbacUserService userService;

    @Autowired
    private RbacRoleService roleService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        RbacUser user = (RbacUser) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<RbacRole> list = roleService.getRoleByUserId(user.getId());
        Set<String> set = new HashSet<>();
        for (RbacRole r : list) {
            set.add(r.getName());
        }
        /*
        Set<String> perms = new HashSet<String>();
        List<Permission> list1 = this.iPermissionService.findRolePerm(user.getNickname());
        for(Permission p : list1) {
            perms.add(p.getUrl());
        }
        */
        if(set.size() !=0 ){
            info.setRoles(set);
        }//添加角色集合   @RequireRoles("admin")会到info中寻找 字符串 "admin"
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
