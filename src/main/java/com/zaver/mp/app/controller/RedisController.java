package com.zaver.mp.app.controller;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.app.model.User;
import com.zaver.mp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private StringRedisTemplate strRedis;
    @GetMapping("/info/{id}")
    public Result redisInfo(@PathVariable("id") String id){
        // stringRedis.opsForValue().set("local","host");
        return Result.ok(strRedis.opsForValue().get(id));
    }

    @PostMapping("/json")
    public Result redisSave(User user){
        strRedis.opsForValue().set("json:user",JSON.toJSONString(user));
        String jsonUser = strRedis.opsForValue().get("json:user");
        User user1 = JSON.parseObject(jsonUser, User.class);
        return Result.ok(user1);
    }
}
