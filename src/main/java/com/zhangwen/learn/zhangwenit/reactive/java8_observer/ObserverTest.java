package com.zhangwen.learn.zhangwenit.reactive.java8_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description java8的观察者模式实现，在java9之后中已经被淘汰
 * 缺点： 1.底层使用的Vector保存的，全线程安全
 * 2.没有实现泛型
 * 3.同步 -> 阻塞 （{@link Observable#notifyObservers(Object arg)} -> synchronized (this)）
 * @Author ZWen
 * @Date 2019/3/1 10:18 AM
 * @Version 1.0
 * @see Observable,Observer
 **/
public class ObserverTest {

    public static void main(String[] args) {

        MyObservable observable = new MyObservable();

        observable.addObserver((o, arg) -> System.out.println("第一个订阅者收到的消息是: " + arg));
        observable.addObserver((o, arg) -> System.out.println("第二个订阅者收到的消息是: " + arg));

        observable.setChanged();

        observable.notifyObservers("hello,world");
    }

    private static class MyObservable extends Observable {

        /**
         * Marks this {@code Observable} object as having been changed; the
         * {@code hasChanged} method will now return {@code true}.
         */
        @Override
        public void setChanged() {
            super.setChanged();
        }
    }
}