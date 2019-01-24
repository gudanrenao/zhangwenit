package com.zhangwen.learn.zhangwenit.designmodel.builder.hot;

import com.zhangwen.learn.zhangwenit.designmodel.builder.taste.SourTaste;
import com.zhangwen.learn.zhangwenit.designmodel.builder.taste.Taste;

/**
 * @Description 酸菜鱼
 * @Author ZWen
 * @Date 2019/1/24 1:47 PM
 * @Version 1.0
 **/
public class FishFood extends AbstractHotFoodItem{
    /**
     * 名称
     *
     * @return
     */
    @Override
    public String name() {
        return "酸菜鱼";
    }

    /**
     * 价格 单位/分
     *
     * @return
     */
    @Override
    public int price() {
        return 5800;
    }

    /**
     * 口味
     *
     * @return
     */
    @Override
    public Taste taste() {
        return new SourTaste();
    }
}