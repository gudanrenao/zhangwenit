package com.zhangwen.learn.zhangwenit.designmodel.builder;

import com.zhangwen.learn.zhangwenit.designmodel.builder.taste.Taste;

/**
 * @Description 菜单单品
 * @Author ZWen
 * @Date 2019/1/24 12:53 PM
 * @Version 1.0
 **/
public interface FoodItem {

    /**
     * 名称
     *
     * @return
     */
    String name();

    /**
     * 价格 单位/分
     *
     * @return
     */
    int price();

    /**
     * 是否是热菜
     *
     * @return
     */
    boolean isHot();

    /**
     * 口味
     *
     * @return
     */
    Taste taste();
}
