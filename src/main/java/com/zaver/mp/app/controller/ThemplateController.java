package com.zaver.mp.app.controller;


import com.alibaba.fastjson.JSON;
import com.zaver.mp.app.model.User;
import com.zaver.mp.rbac.model.RbacUser;
import com.zaver.mp.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName : ThemplateController
 * @Description TODO
 * @Date : 2019/4/14 23:23
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */

@Controller
@RequestMapping("/th")
public class ThemplateController {

    @RequestMapping("/login")
    public String login(RbacUser user, Model model){
        System.out.println("进入login name = " + JSON.toJSONString(user));
        String username = user.getUsername();
        String password = user.getPassword();
        if(StringUtils.isNull(username)||StringUtils.isNull(password)){
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "redirect:index";
        } catch (UnknownAccountException e) {
            // 用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            // 密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @RequestMapping("/index")
    public String toLogin(){
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}

