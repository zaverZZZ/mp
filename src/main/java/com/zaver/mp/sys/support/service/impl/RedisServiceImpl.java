package com.zaver.mp.sys.support.service.impl;

import com.zaver.mp.modules.app.model.User;
import com.zaver.mp.sys.support.service.RedisService;
import com.zaver.mp.utils.Constants;
import com.zaver.mp.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @ClassName : RedisServiceImpl
 * @Description TODO
 * @Date : 2019/5/5 15:08
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void setAppUserInfo(User user){
        redisUtils.setHash(Constants.REDIS_USERINFO,String.valueOf(user.getId()),user);
    }
    @Override
    public User getAppUserInfo(Integer id){
        return redisUtils.getHash(Constants.REDIS_USERINFO, String.valueOf(id), User.class);
    }

    @Override
    public void setIdempotentToken(String token) {
        redisUtils.setStr(Constants.REDIS_TOKEN_IDEMPOTENT+token,token,Constants.REDIS_TIME_IDEMPOTENT);
    }

    @Override
    public Boolean deleteIdempotentToken(String token) {
        return redisUtils.delete(Constants.REDIS_TOKEN_IDEMPOTENT+token);
    }
}
