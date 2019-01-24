package com.zhangwen.learn.zhangwenit.designmodel.builder.taste;

/**
 * @Description 辣
 * @Author ZWen
 * @Date 2019/1/24 1:52 PM
 * @Version 1.0
 **/
public class SpicyTaste implements Taste{
    @Override
    public TasteEnum getTaste() {
        return TasteEnum.SPICY;
    }

    @Override
    public String toString() {
        return "Spicy";
    }
}