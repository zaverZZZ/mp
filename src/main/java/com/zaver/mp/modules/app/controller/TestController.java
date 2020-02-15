package com.zaver.mp.modules.app.controller;

import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.annotation.ApiIdempotent;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName : RedirectController
 * @Description TODO
 * @Date : 2020/1/19 11:02
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/app/test")
public class TestController {

    @ApiIdempotent
    @GetMapping("/test1")
    public Result test1(){
        return Result.ok();
    }

    @RequiresRoles("admin")
    @GetMapping("/test2")
    public Result testRedis(){
        return Result.ok();
    }
}
