package com.zhangwen.learn.zhangwenit.designmodel.singleinstance;

/**
 * @Description 单例模式：双重检测机制 懒汉模式 多线程安全，高性能
 * @Author ZWen
 * @Date 2019/1/19 10:23 AM
 * @Version 1.0
 **/
public class SingleInstance3 {

    private final String name;

    /**
     * volatile 防止指令重排
     */
    private volatile static SingleInstance3 instance3;

    private SingleInstance3(String name) {
        this.name = name;
    }

    public static final SingleInstance3 getInstance() {
        if (instance3 == null) {
            synchronized (SingleInstance3.class) {
                if (instance3 == null) {
                    instance3 = new SingleInstance3("zw: " + System.currentTimeMillis());
                }
            }
        }
        return instance3;
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