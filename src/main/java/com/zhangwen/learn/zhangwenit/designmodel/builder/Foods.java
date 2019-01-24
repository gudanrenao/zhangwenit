package com.zhangwen.learn.zhangwenit.designmodel.builder;

import java.util.List;

/**
 * @Description 下单的菜品集合
 * @Author ZWen
 * @Date 2019/1/24 1:31 PM
 * @Version 1.0
 **/
public class Foods {

    private List<FoodItem> foodItemList;

    public void add(FoodItem foodItem) {
        this.foodItemList.add(foodItem);
    }

    public int totalPrice() {
        return this.foodItemList.stream().mapToInt(FoodItem::price).sum();
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public void print() {
        System.out.println("下单的菜品如下: ");
        this.foodItemList.forEach(System.out::println);
        System.out.println("下单的菜品总价为: " + totalPrice() / 100.0 + "元");
    }
}