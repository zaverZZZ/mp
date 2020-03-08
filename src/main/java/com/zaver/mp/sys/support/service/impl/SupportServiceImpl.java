package com.zaver.mp.sys.support.service.impl;

import com.zaver.mp.sys.support.service.RedisService;
import com.zaver.mp.sys.support.service.SupportService;
import com.zaver.mp.utils.IdWorker;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.StringUtils;
import com.zaver.mp.utils.exception.LocalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : SupportServiceImpl
 * @Description TODO
 * @Date : 2020/2/15 2:40
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private RedisService redisService;

    @Override
    public String getIdempotentToken() {
        IdWorker idWorker = new IdWorker();
        String token = String.valueOf(idWorker.nextId());
        redisService.setIdempotentToken(token);
        return token;
    }

    @Override
    public Boolean checkAndDeleteIdempotentToken(String token) {
        if(StringUtils.isEmpty(token)){
            return false;
        }
        Boolean del = redisService.deleteIdempotentToken(token);
        if(!del){
            return false;
        }
        return true;
    }
}
