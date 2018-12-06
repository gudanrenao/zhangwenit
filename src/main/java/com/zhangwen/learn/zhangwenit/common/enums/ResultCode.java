package com.zhangwen.learn.zhangwenit.common.enums;

/**
 * @author zw
 */
@SuppressWarnings("all")
public enum ResultCode {

    SUCCESS(0, "成功"),
    FAILED(-1, "失败"),

    ERROR(3, "系统繁忙，请稍后重试"),
    ILLEGAL_OPERATION(4, "非法操作"),
    FORBIDDEN(5, "禁止"),
    EXISTS(6, "已经存在"),
    SERVICE_UNAVAILABLE(7, "服务不可调用"),
    OVER_LIMIT(8, "访问超限"),
    NOT_EXISTS(9, "不存在"),
    STATUS_INCORRECT(10, "状态不符"),
    MISSING_ARGUMENT(11, "参数确实"),
    NEED_LOGIN(12, "需要登陆");

    private int index;
    private String desc;

    ResultCode(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}
