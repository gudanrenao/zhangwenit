package com.zhangwen.learn.zhangwenit.jvm.javap;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 泛型擦除
 * @Author ZWen
 * @Date 2020/4/7 11:50 AM
 * @Version 1.0
 **/
public class GenericityCase {

    public static void main(String[] args) {
        //编译后会泛型都变回了裸类型(Map())
        Map<String,String> map = new HashMap<String,String>();
        map.put("nihao","world");
        map.put("2","zw");

        //编译后取值会做强制类型转换 (String)map.get("2")
        System.out.println(map.get("2"));
        System.out.println(map.get("nihao"));



    }
}