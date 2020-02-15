package com.zaver.mp.utils.aspect;

import com.zaver.mp.sys.support.service.SupportService;
import com.zaver.mp.utils.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : ApiIdempotentAspect
 * @Description TODO
 * @Date : 2020/2/15 0:02
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Aspect
@Component
public class ApiIdempotentAspect {

    @Autowired
    private SupportService supportService;

    // 切点注解，通过验证redis中是否存在对应token，检验幂等性。
    @Before("@annotation(com.zaver.mp.utils.annotation.ApiIdempotent)")
    public void idempotent(){
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader(Constants.REQUEST_HEADER_IDEMPOTENT);
        supportService.checkIdempotentToken(token);
    }
}
