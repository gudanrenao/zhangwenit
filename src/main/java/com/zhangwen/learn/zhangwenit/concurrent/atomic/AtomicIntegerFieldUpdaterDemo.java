package com.zhangwen.learn.zhangwenit.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description AtomicIntegerFieldUpdater/AtomicLongFieldUpdater/AtomicReferenceFieldUpdater类似
 * 注意：更新类的字段必须使用 public volatile 修饰
 * @Author ZWen
 * @Date 2019/4/8 6:40 PM
 * @Version 1.0
 **/
public class AtomicIntegerFieldUpdaterDemo {

    private static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        User user = new User("zw", 10);

        int beforeOld = updater.getAndIncrement(user);
        System.out.println(beforeOld);
        System.out.println(updater.get(user));
        System.out.println(user);
    }

    public static class User {
        private String name;
        /**
         * 更新类的字段必须使用 public volatile 修饰
         */
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
}