package com.zaver.mp.utils.security;

import com.zaver.mp.utils.IdWorker;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName : CustomSessionIdGenerator
 * @Description TODO
 * @Date : 2020/2/14 1:56
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Component
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    @Override
    public Serializable generateId(Session session) {
        IdWorker idWorker = new IdWorker();
        Long l = idWorker.nextId();
        return l;
    }
}
