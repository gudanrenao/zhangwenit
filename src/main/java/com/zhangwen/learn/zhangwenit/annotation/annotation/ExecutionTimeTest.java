package com.zhangwen.learn.zhangwenit.annotation.annotation;

import java.lang.annotation.*;

/**
 * 接口执行消耗时间统计注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecutionTimeTest {
    String value() default "";
}
