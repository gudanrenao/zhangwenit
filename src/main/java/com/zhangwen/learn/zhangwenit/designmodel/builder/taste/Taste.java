package com.zhangwen.learn.zhangwenit.designmodel.builder.taste;

/**
 * @Description 单品口味
 * @Author ZWen
 * @Date 2019/1/24 1:04 PM
 * @Version 1.0
 **/
public interface Taste {



    TasteEnum getTaste();

    /**
     * 酸甜苦辣
     */
    @SuppressWarnings("all")
    enum TasteEnum {
        SWEET, SOUR, BITTER, SPICY
    }
}
