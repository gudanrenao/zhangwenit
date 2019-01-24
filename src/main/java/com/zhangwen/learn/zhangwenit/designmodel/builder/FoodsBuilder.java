package com.zhangwen.learn.zhangwenit.designmodel.builder;

import com.zhangwen.learn.zhangwenit.designmodel.builder.cold.CoffeeFood;
import com.zhangwen.learn.zhangwenit.designmodel.builder.cold.PeerFood;
import com.zhangwen.learn.zhangwenit.designmodel.builder.hot.ChickenFood;
import com.zhangwen.learn.zhangwenit.designmodel.builder.hot.FishFood;

import java.util.ArrayList;

/**
 * @Description 菜品集合创建者
 * @Author ZWen
 * @Date 2019/1/24 1:48 PM
 * @Version 1.0
 **/
public class FoodsBuilder {


    /**
     * 辣子鸡和咖啡套餐
     *
     * @return
     */
    public Foods chickenAndCoffee() {
        Foods foods = new Foods();
        foods.setFoodItemList(new ArrayList<>(2));
        foods.add(new ChickenFood());
        foods.add(new CoffeeFood());
        return foods;
    }

    /**
     * 酸菜鱼和啤酒套餐
     *
     * @return
     */
    public Foods fishAndPeer() {
        Foods foods = new Foods();
        foods.setFoodItemList(new ArrayList<>(2));
        foods.add(new FishFood());
        foods.add(new PeerFood());
        return foods;
    }

}