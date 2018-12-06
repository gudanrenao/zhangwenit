package com.zhangwen.learn.zhangwenit.enums;

/**
 * @Description 异常信息枚举
 * @Author ZWen
 * @Date 2018/12/5 3:38 PM
 * @Version 1.0
 **/
@SuppressWarnings("all")
public enum ExceptionEnum {
    LOGIN_NAME_ERROR(600, "不存在该用户"),
    LOGIN_NO_ROLE_ERROR(601, "用户无权限");

    private final int errorCode;
    private final String errorMsg;

    ExceptionEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
