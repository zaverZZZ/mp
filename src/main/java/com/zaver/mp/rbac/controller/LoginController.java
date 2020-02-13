package com.zaver.mp.rbac.controller;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.rbac.model.LoginForm;
import com.zaver.mp.rbac.model.RbacUser;
import com.zaver.mp.rbac.service.RbacUserService;
import com.zaver.mp.rbac.service.impl.RedisServiceImpl;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.StringUtils;
import com.zaver.mp.utils.exception.LocalException;
import javafx.fxml.LoadException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName : LoginController
 * @Description TODO
 * @Date : 2019/5/5 17:43
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/sys")
public class LoginController {

    @Autowired
    RbacUserService rbacUserService;
    @Autowired
    RedisServiceImpl redisService;
    @RequestMapping("/login")
    public Result login(@RequestBody LoginForm loginForm){

        String username =  loginForm.getUsername();
        String password =  loginForm.getPassword();
        RbacUser rbacUser = rbacUserService.getOneByUserName(username);
        if(StringUtils.isNull(username)||StringUtils.isNull(password)){
            return Result.fail("请输入账号密码");
        }
        if(rbacUser==null){
            return Result.fail("用户名不存在");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            // TODO 注意防止token重复
            String sessionId = subject.getSession().getId().toString();
            redisService.setSysUserToken(rbacUser.getId(),sessionId);
            return Result.ok(sessionId);
        } catch (UnknownAccountException e) {
            // 用户名不存在
            return Result.fail("用户名不存在");
        }catch (IncorrectCredentialsException e) {
            // 密码错误
            return Result.fail("密码错误");
        }
    }

    @RequestMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        RbacUser user = (RbacUser)subject.getPrincipal();
        if(user==null) throw new LocalException(Result.fail("未查询到登录用户"));
        subject.logout();
        redisService.deleteSysUserTokenById(user.getId());
        return Result.ok();
    }

    @RequestMapping("/unLogin")
    public Result unLogin(){
        return Result.fail("请登录后使用");
    }
}
