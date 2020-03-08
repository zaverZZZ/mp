package com.zaver.mp.utils.aspect;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.sys.rbac.model.RbacUser;
import com.zaver.mp.sys.rbac.service.RbacUserService;
import com.zaver.mp.sys.support.model.SysLog;
import com.zaver.mp.sys.support.service.SysLogService;
import com.zaver.mp.utils.HttpUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName : SysLogAspect
 * @Description TODO
 * @Date : 2020/3/8 22:03
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private RbacUserService userService;

    @Around("@annotation(com.zaver.mp.utils.annotation.SysLog)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }
    // TODO 系统日志暂行办法，不能保存在数据库，并且要使用异步操作(mq等)
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        com.zaver.mp.utils.annotation.SysLog logAnnotation = method.getAnnotation(com.zaver.mp.utils.annotation.SysLog.class);
        if(logAnnotation != null){
            //注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSON.toJSONString(args[0]);
            sysLog.setParams(params);
        }catch (Exception e){

        }

        //获取request
        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(HttpUtil.getRemoteHost(request));

        //用户名
        String username = ((RbacUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);
        RbacUser oneByUserName = userService.getOneByUserName(username);
        sysLog.setUserId(oneByUserName.getId());

        sysLog.setTime(time);
        sysLog.setCreateDate(System.currentTimeMillis());
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
