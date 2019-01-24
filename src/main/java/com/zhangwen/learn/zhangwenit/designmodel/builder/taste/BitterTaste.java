package com.zhangwen.learn.zhangwenit.designmodel.builder.taste;

/**
 * @Description 苦
 * @Author ZWen
 * @Date 2019/1/24 1:50 PM
 * @Version 1.0
 **/
public class BitterTaste implements Taste {

    @Override
    public TasteEnum getTaste() {
        return TasteEnum.BITTER;
    }

    @Override
    public String toString() {
        return "Bitter";
    }
}