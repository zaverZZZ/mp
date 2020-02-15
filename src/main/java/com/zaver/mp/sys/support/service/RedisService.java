package com.zaver.mp.sys.support.service;

import com.zaver.mp.modules.app.model.User;

import java.io.Serializable;

public interface RedisService {
    //TODO  测试
    void setAppUserInfo(User user);
    //TODO  测试
    User getAppUserInfo(Integer id);
    // 写入幂等性token
    void setIdempotentToken(String token);
    // 删除幂等性token
    Boolean deleteIdempotentToken(String token);
}
