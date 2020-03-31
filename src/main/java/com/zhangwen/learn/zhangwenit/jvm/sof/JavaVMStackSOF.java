package com.zhangwen.learn.zhangwenit.jvm.sof;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/3/24 10:09 PM
 * @Version 1.0
 **/
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length is " + stackSOF.stackLength);
            throw e;
        }
    }
}