package com.zhangwen.learn.zhangwenit.designmodel.builder.taste;

/**
 * @Description 酸食
 * @Author ZWen
 * @Date 2019/1/24 1:44 PM
 * @Version 1.0
 **/
public class SourTaste implements Taste {

    @Override
    public TasteEnum getTaste() {
        return TasteEnum.SOUR;
    }

    @Override
    public String toString() {
        return "Sour";
    }
}