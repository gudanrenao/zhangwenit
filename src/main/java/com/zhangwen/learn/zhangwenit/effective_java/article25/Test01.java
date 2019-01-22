package com.zhangwen.learn.zhangwenit.effective_java.article25;

import com.google.common.collect.Lists;
import com.zhangwen.learn.zhangwenit.api.system.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/22 9:45 PM
 * @Version 1.0
 **/
public class Test01 {

    /**
     * 列表优先于数组
     * 1.在类型检测时，数组是运行时检测出错误，而列表在编译时就能发现错误
     * 2.数组和泛型不可以混合使用
     */
    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList("1", "2");
        list.add("3");

        //运行时异常
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "I am String";

        //编译时异常
//        List<Object> objectList = new ArrayList<Long>();
//        objectList.add("I am String");

        String[] strings = list.toArray(new String[0]);


        //toArray safe
        //toArray返回的数组是一个新的数组，如果里面的是简单的数据类型或者String,那么修改返回的数组的内容，不会影响其他数组或原来的List
        //但是，如果里面的是引用类型，那么其实toArray还是一个浅拷贝，修改引用类型的某个字段，会影响到其他的数组或List,因为他们持有的
        // 都是引用类型的引用，实际元素对应的都是同一个内存空间
        User user = new User();
        user.setId(1L);
        ArrayList<User> userArrayList = Lists.newArrayList(user);
        User[] userArray1 = userArrayList.toArray(new User[0]);
        User[] userArray2 = userArrayList.toArray(new User[0]);
        System.out.println(userArray1 == userArray2);
        userArray1[0].setId(2L);
        System.out.println(Arrays.toString(userArray1));
        System.out.println(Arrays.toString(userArray2));
        System.out.println(userArrayList);
    }



    /**
     * toArray() 在内部锁定列表？ todo:为什么？
     */
    public static Object[] listToArray(List list) {
        Object[] array = list.toArray();
        return array;
    }
}