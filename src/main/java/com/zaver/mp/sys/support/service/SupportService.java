package com.zaver.mp.sys.support.service;

public interface SupportService{
    // 获取用于幂等性的token
    String getIdempotentToken();
    // 检验幂等性token
    void checkIdempotentToken(String token);
}
