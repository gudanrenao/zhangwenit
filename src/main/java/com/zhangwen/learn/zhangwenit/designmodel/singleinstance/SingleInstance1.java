package com.zhangwen.learn.zhangwenit.designmodel.singleinstance;

/**
 * @Description 单例模式：1.枚举 推荐 饿汉模式 多线程安全
 * @Author ZWen
 * @Date 2019/1/19 10:23 AM
 * @Version 1.0
 **/
public class SingleInstance1 {

    private final String name;

    private SingleInstance1(String name) {
        this.name = name;
    }

    enum InstanceHolder {
        /**
         * JVM保证单例
         */
        INSTANCE;

        /**
         * JVM保证只运行一次
         */
        InstanceHolder() {
            this.singleInstance1 = new SingleInstance1("zw:" + System.currentTimeMillis());
        }

        private SingleInstance1 singleInstance1;

        public SingleInstance1 getSingleInstance1() {
            return singleInstance1;
        }
    }

    public static final SingleInstance1 getInstance() {
        return InstanceHolder.INSTANCE.getSingleInstance1();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SingleInstance1{" +
                "name='" + name + '\'' +
                '}';
    }
}