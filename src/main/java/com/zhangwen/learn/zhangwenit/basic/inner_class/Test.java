package com.zhangwen.learn.zhangwenit.basic.inner_class;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/31 5:16 PM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        //创建内部类语法 .new
        Out out = new Out();
        Out.Inner inner = out.new Inner();
        inner.print();

        //.this获取外部类
        inner.getOutClass().hello();

    }

}