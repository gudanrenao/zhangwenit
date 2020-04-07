package com.zhangwen.learn.zhangwenit.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;

/**
 * @Description 方法句柄演示
 * @Author ZWen
 * @Date 2020/4/4 10:32 PM
 * @Version 1.0
 **/
public class MethodHandleCase {

    static class MyPrintln {

        public void println(String s) {
            System.out.println("MyPrintln : " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = new Random().nextBoolean() ? System.out : new MyPrintln();

        getPrintlnMH(obj).invokeExact("hello world!");
    }

    /**
     * Reflection和MethodHandle机制本质上都是在模拟方法调用，但是Reflection是在模拟Java代码层次的方法调用，
     * 而MethodHandle是在模拟字节码层次的方法调用。在MethodHandles.Lookup上的3个方法findStatic()、
     * findVirtual()、findSpecial()正是为了对应于invokestatic、invokevirtual（以及invokeinterface）
     * 和invokespecial这几条字节码指令的执行权限校验行为，而这些底层细节在使用Reflection API时是不需要关心的
     *
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    private static MethodHandle getPrintlnMH(Object obj) throws NoSuchMethodException, IllegalAccessException {
        //methodType代表方法类型，包含了方法的返回值（methodType()第一个参数）以及参数列表（methodType()第二个及之后的参数）
        MethodType methodType = MethodType.methodType(void.class, String.class);

        return MethodHandles.lookup().findVirtual(obj.getClass(), "println", methodType).bindTo(obj);
    }
}