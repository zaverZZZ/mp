package com.zaver.mp.utils.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, String> listOperations;
    @Autowired
    private SetOperations<String, String> setOperations;
    @Autowired
    private ZSetOperations<String, String> zSetOperations;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1L;

    public void setStr(String key, Object value, long expire) {
        if (expire <= 0L){
            valueOperations.set(key,toJson(value));
        }else{
            valueOperations.set(key,toJson(value),expire,TimeUnit.SECONDS);
        }
    }

    public void setStr(String key, Object value) {
        setStr(key, value, NOT_EXPIRE);
    }

    public <T> T getStr(String key, Class<T> clazz) {
        String value = valueOperations.get(key);
        return value == null ? null : fromJson(value, clazz);
    }

    public String getStr(String key) {
        String value = valueOperations.get(key);
        return toJson(value);
    }

    public void setHash(String key,String hashkey,Object value){
        hashOperations.put(key,hashkey,value);
    }

    public String getHash(String key,String hashkey){
        Object o = hashOperations.get(key, hashkey);
        return toJson(o);
    }

    public <T> T getHash(String key,String hashkey,Class<T> clazz){
        String value = getHash(key, hashkey);
        return value == null ? null : fromJson(value, clazz);
    }

    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        /*if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String ||
                object instanceof Short || object instanceof Character || object instanceof Byte) {
            return String.valueOf(object);
        }*/
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}