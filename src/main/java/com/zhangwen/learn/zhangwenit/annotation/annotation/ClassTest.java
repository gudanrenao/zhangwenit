package com.zhangwen.learn.zhangwenit.annotation.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassTest {
    String value() default "";
}
