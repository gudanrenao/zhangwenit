package com.zhangwen.learn.zhangwenit.basic.inner_class;

/**
 * @Description <p>
 * <p>
 * 为什么使用内部类：
 * 1.可以解决多重继承的问题，外部类继承一个类后，内部类继承另一个类，那么内部类就同时拥有了这两个继承的类的能力
 * <p>
 * @Author ZWen
 * @Date 2019/5/31 5:14 PM
 * @Version 1.0
 **/
public class Out {

    private int age = 100;

    public void hello() {
        System.out.println("outer class hello...");
    }

    public Inner getInner() {
        return new Inner();
    }

    /**
     * 普通内部类 不可以有嵌套内部类
     * 普通内部类 隐式保存了一个引用，指向创建它的外部类对象
     * 创建普通内部类时必须先创建其外部类
     * <p>
     * 使用建议：
     * 1.它可以访问外部类的所有域和方法
     */
    public class Inner {

        private String name;

        //普通内部类不可以有静态方法和静态成员变量
//        private static int sex;

        public void print() {
            System.out.println("out class age is " + age);
        }

        /**
         * 使用 .this 语法 获取外部类引用
         *
         * @return
         */
        public Out getOutClass() {
            return Out.this;
        }
    }

    /**
     * 静态内部类也叫嵌套类
     * 嵌套类可以和外部类没有依赖关系
     * <p>
     * 使用建议：
     * 1.如果你有一些公共代码，希望可以被某个接口的所有不同实现所公用，那么使用接口内部的嵌套类很适合
     * 2.可以使用嵌套类测试外部类的代码，发布的时候，如果不希望包含测试代码，可以删除生成的Out$InnerTest.class文件
     */
    public static class StaticInner {

        private String name;

        //静态内部类可以有静态方法和静态成员变量
        private static int sex;

        /**
         * 静态内部类的方法无法直接使用外部类的非静态域
         */
        public void print() {
//            System.out.println("out class age is " + age);
        }
    }


}