package com.zaver.mp.rbac.service.impl;

import com.zaver.mp.app.model.User;
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
public class RedisServiceImpl {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 保存登录token到redis，key：Constants.REDIS_TOKEN_USERLOGIN+":"+id
     * @param id 用户id
     * @param token token
     * @param expireTime 过期时间
     */
    public void setSysUserToken(Serializable id, String token, long expireTime){
        redisUtils.setStr(Constants.REDIS_TOKEN_USERLOGIN+":"+id,token,expireTime);
    }

    public void setSysUserToken(Serializable id, String token){
        redisUtils.setStr(Constants.REDIS_TOKEN_USERLOGIN+":"+id,token);
    }

    public String getSysUserTokenById(Serializable id){
        String s = redisUtils.getStr(Constants.REDIS_TOKEN_USERLOGIN + ":" + id);
        return s;
    }

    public void deleteSysUserTokenById(Serializable id){
        redisUtils.delete(Constants.REDIS_TOKEN_USERLOGIN + ":" + id);
    }

    public void setAppUserInfo(User user){
        redisUtils.setHash(Constants.REDIS_USERINFO,String.valueOf(user.getId()),user);
    }

    public User getAppUserInfo(Integer id){
        return redisUtils.getHash(Constants.REDIS_USERINFO, String.valueOf(id), User.class);
    }
}
