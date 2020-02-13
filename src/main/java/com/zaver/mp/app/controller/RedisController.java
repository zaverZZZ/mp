package com.zaver.mp.app.controller;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.app.model.User;
import com.zaver.mp.rbac.service.impl.RedisServiceImpl;
import com.zaver.mp.utils.Constants;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : RedisController
 * @Description TODO
 * @Date : 2019/4/15 0:21
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisServiceImpl redisService;

    @GetMapping("/token/{id}")
    public Result tokenInfo(@PathVariable("id") String id){
        String tokenById = redisService.getSysUserTokenById(id);
        return Result.ok(tokenById);
    }

    @PostMapping("/user")
    public Result userInfoSave(User user){
        redisService.setAppUserInfo(user);
        User appUserInfo = redisService.getAppUserInfo(user.getId());
        return Result.ok(appUserInfo);
    }
}
