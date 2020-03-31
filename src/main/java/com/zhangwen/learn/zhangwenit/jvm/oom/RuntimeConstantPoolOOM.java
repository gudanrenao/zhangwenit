package com.zhangwen.learn.zhangwenit.jvm.oom;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/3/24 10:52 PM
 * @Version 1.0
 **/
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        //true
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        //false toString之前，java在内存中已经有了，加载sun.misc.Version时，所以
        //str2是一个新地址，str2.intern()是之前已经存在于堆内存中的地址
        System.out.println(str2.intern() == str2);
    }
}