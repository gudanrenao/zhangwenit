package com.zhangwen.learn.zhangwenit.annotation.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTest {
    String value() default "";
}
