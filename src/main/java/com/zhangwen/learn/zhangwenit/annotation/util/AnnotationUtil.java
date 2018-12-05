package com.zhangwen.learn.zhangwenit.annotation.util;

import com.zhangwen.learn.zhangwenit.annotation.annotation.ExecutionTimeTest;
import com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest;

import java.lang.reflect.Field;

/**
 * 注解工具类
 *
 * @author zhangwen at 2018-12-06 0:13
 **/
public class AnnotationUtil {

    public static void getFruitInfo(Class<?> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyTest.class)) {
                MyTest annotation = field.getAnnotation(MyTest.class);
                System.out.println("custom annotation is MyTest,value is:" + annotation.value());
            } else if (field.isAnnotationPresent(ExecutionTimeTest.class)) {
                ExecutionTimeTest annotation = field.getAnnotation(ExecutionTimeTest.class);
                System.out.println("custom annotation is ExecutionTimeTest,value is:" + annotation.value());
            } else {
                System.out.println("no custom annotation");
            }
        }
    }
}
