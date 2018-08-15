package com.zhangwen.learn.zhangwenit.exception;

/**
 * @author zhangwen at 2018-08-16 1:21
 **/
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
