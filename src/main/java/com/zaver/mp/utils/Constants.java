package com.zaver.mp.utils;

public interface Constants {
    // 测试数据
    String REDIS_USERINFO = "app:userinfo";
    // redis中用户登录的token key
    String REDIS_TOKEN_USERLOGIN = "sys:user_token";
    // redis中用于确保接口幂等性的token key
    String REDIS_TOKEN_IDEMPOTENT = "sys:idempotent:";
    // 幂等性超时时间 单位秒
    Long REDIS_TIME_IDEMPOTENT = 5 * 60L;
    // request的header中的key
    String REQUEST_HEADER_AUTHIRIZATION = "User-Token";
    String REQUEST_HEADER_IDEMPOTENT = "Idempotent-Token";
}
