package com.zhangwen.learn.zhangwenit.designmodel.builder.cold;

import com.zhangwen.learn.zhangwenit.designmodel.builder.taste.BitterTaste;
import com.zhangwen.learn.zhangwenit.designmodel.builder.taste.Taste;

/**
 * @Description 咖啡
 * @Author ZWen
 * @Date 2019/1/24 1:42 PM
 * @Version 1.0
 **/
public class CoffeeFood extends AbstractCodeFoodItem {
    /**
     * 名称
     *
     * @return
     */
    @Override
    public String name() {
        return "coffee";
    }

    /**
     * 价格 单位/分
     *
     * @return
     */
    @Override
    public int price() {
        return 2000;
    }

    /**
     * 口味
     *
     * @return
     */
    @Override
    public Taste taste() {
        return new BitterTaste();
    }

}