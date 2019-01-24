package com.zhangwen.learn.zhangwenit.designmodel.builder.taste;

/**
 * @Description 甜食
 * @Author ZWen
 * @Date 2019/1/24 1:44 PM
 * @Version 1.0
 **/
public class SweetTaste implements Taste {

    @Override
    public TasteEnum getTaste() {
        return TasteEnum.SWEET;
    }

    @Override
    public String toString() {
        return "Sweet";
    }
}