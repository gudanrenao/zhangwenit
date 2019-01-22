package com.zhangwen.learn.zhangwenit.designmodel.singleinstance;

/**
 * @Description 单例模式：静态内部类 推荐 懒汉模式 多线程安全
 * @Author ZWen
 * @Date 2019/1/19 10:23 AM
 * @Version 1.0
 **/
public class SingleInstance2 {

    private final String name;

    private SingleInstance2(String name) {
        this.name = name;
    }

    /**
     * 利用了classLoader机制来保证初始化时只有一个线程
     */
    private static class InstanceHolder {
        private static final SingleInstance2 SINGLE_INSTANCE_2 = new SingleInstance2("zw: " + System.currentTimeMillis());
    }

    /**
     * 只有显式调用了getInstance，InstanceHolder才会被装载，从而SingleInstance2才会被实例化
     */
    public static final SingleInstance2 getInstance() {
        return InstanceHolder.SINGLE_INSTANCE_2;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SingleInstance2{" +
                "name='" + name + '\'' +
                '}';
    }
}