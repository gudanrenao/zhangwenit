package com.zhangwen.learn.zhangwenit.designmodel.builder.cold;

import com.zhangwen.learn.zhangwenit.designmodel.builder.FoodItem;

/**
 * @Description 冷 单品
 * @Author ZWen
 * @Date 2019/1/24 1:17 PM
 * @Version 1.0
 **/
public abstract class AbstractCodeFoodItem implements FoodItem {

    /**
     * 是否是热菜
     *
     * @return
     */
    @Override
    public boolean isHot() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("菜名:%s, 价格:%f, 是否是热菜:%s, 口味:%s", name(), price() / 100.0, isHot() ? "是" : "否", taste());
    }
}