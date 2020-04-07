package com.zhangwen.learn.zhangwenit.jvm.javap;

/**
 * @Description 语法糖：内部类，实际会生成两个class文件，内部类为 InnerClassCase$InnerClass.class
 * @Author ZWen
 * @Date 2020/4/7 2:44 PM
 * @Version 1.0
 **/
public class InnerClassCase {


    public static class InnerCase {
         private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}