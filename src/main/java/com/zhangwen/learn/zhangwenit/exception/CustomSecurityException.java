package com.zhangwen.learn.zhangwenit.exception;

import com.zhangwen.learn.zhangwenit.enums.ExceptionEnum;

/**
 * @Description 自定义安全异常
 * @Author ZWen
 * @Date 2018/12/5 3:36 PM
 * @Version 1.0
 **/
public class CustomSecurityException extends RuntimeException {

    public CustomSecurityException(String message) {
        super(message);
    }

    public CustomSecurityException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMsg());
    }
}