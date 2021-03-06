package com.zaver.mp.utils.annotation;

import java.lang.annotation.*;

// 幂等性注解
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiIdempotent {
}
