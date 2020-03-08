package com.zaver.mp.sys.rbac.controller;

import com.zaver.mp.sys.rbac.model.LoginForm;
import com.zaver.mp.sys.rbac.model.RbacUser;
import com.zaver.mp.sys.rbac.service.RbacUserService;
import com.zaver.mp.sys.support.service.RedisService;
import com.zaver.mp.utils.BeanUtil;
import com.zaver.mp.utils.Constants;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.StringUtils;
import com.zaver.mp.utils.exception.LocalException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@RequestMapping("/sys/rbac")
public class LoginController {

    @Autowired
    RbacUserService rbacUserService;

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
            String sessionId = subject.getSession().getId().toString();
            // redisService.setSysUserToken(rbacUser.getId(),sessionId);
            Map<String, Object> result = BeanUtil.beanToMap(rbacUser);
            result.remove("password");
            result.remove("salt");
            result.put(Constants.REQUEST_HEADER_AUTHIRIZATION,sessionId);
            return Result.ok(result);
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
        return Result.ok();
    }

    @RequestMapping("/unLogin")
    //@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result unLogin(){
        return Result.error(Result.CODE_ERROR_NOTLOGIN,Result.MSG_ERROR_NOTLOGIN);
    }

    @RequestMapping("/notPermit")
    public Result notPermit(){
        return Result.fail("没有权限");
    }

    @RequestMapping("/check")
    public Result check(){return Result.ok();}
}
