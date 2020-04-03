package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description 字段不参与多态
 * @Author ZWen
 * @Date 2020/4/3 11:49 PM
 * @Version 1.0
 **/
public class FieldHasNoPolymorhic {

    static class Father {
        public int i = 1;

        public Father() {
            i = 2;
            sayHello();
        }

        public void sayHello() {
            System.out.println("Father say hello i is " + i);
        }
    }

    static class Sun extends Father {
        public int i = 3;

        public Sun() {
            i = 4;
            sayHello();
        }

        public void sayHello() {
            System.out.println("Sun say hello i is " + i);
        }
    }

    /**
     * Sun say hello i is 0   //由于动态分派，分类的构造方法调用sayHello根据实际类型会执行子类的sayHello。这时i还未被赋值
     * Sun say hello i is 4
     * i is 2  //由于静态分派 通过静态类型访问到了父类中的i，输出了2
     *
     * @param args
     */
    public static void main(String[] args) {
        Father gay = new Sun();
        System.out.println("i is " + gay.i);
    }
}