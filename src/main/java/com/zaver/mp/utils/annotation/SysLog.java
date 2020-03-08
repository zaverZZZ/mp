package com.zaver.mp.utils.annotation;

import java.lang.annotation.*;


// 系统日志
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
