package com.zaver.mp.modules.app.controller;

import com.zaver.mp.modules.app.model.User;
import com.zaver.mp.sys.support.service.RedisService;
import com.zaver.mp.utils.Result;
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
@RequestMapping("/app/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/user")
    public Result userInfoSave(User user){
        redisService.setAppUserInfo(user);
        User appUserInfo = redisService.getAppUserInfo(user.getId());
        return Result.ok(appUserInfo);
    }
}
