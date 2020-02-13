package com.zaver.mp.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * @ClassName : JwtUtil
 * @Description TODO
 * @Date : 2020/2/13 1:22
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class JwtUtil {

    // 默认过期时间 5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
    // 密钥
    private static final String SECRET = "nkjsdvSLKJNasldmas";

    /**
     * 创建token
     * @param username 登录用户名
     * @return
     */
    public static String createToken(String username){
        System.out.println("===========创建token==========");
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username",username)
                .withExpiresAt(expiryDate)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token, String username) {
        System.out.println("===========校验token==========");
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息,无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        if(token == null){
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
