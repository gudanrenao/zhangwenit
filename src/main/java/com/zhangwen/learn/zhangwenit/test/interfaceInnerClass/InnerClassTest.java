package com.zhangwen.learn.zhangwenit.test.interfaceInnerClass;

import com.zhangwen.learn.zhangwenit.api.system.entity.User;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/23 5:02 PM
 * @Version 1.0
 **/
public class InnerClassTest {

    public static void main(String[] args) {
        InnerClassTest classTest = new InnerClassTest();
        User user = new User();
        user.setId(1L);
        classTest.runIt(user);
    }

    public void runIt(User user) {
        //接口的匿名实现类，实际上就是在接口定义的方法内部执行test2(user)
//        User say = say(user, new MyInterface() {
//            @Override
//            public User test() {
//                try {
//                    return test2(user);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw e;
//                }
//            }
//        });
        //返回结果result 是say方法的返回值
        User result = say(user, () -> {
            try {
                return test2(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        });
        System.out.println("result is : " + result);
    }

    public User say(User user, MyInterface myInterface) {
//        user.setName("zw");
        System.out.println("user is : " + user);
        //接下来实际执行的时上面定义的匿名实现类的test2(user)方法
        myInterface.test();
        //然后继续执行
        user.setName("zw");
        System.out.println("user 2 is : " + user);
        return user;
    }

    public User test2(User user) {
        System.out.println("inner user is : " + user);
        user.setPassword("password");
        System.out.println("inner user 2 is : " + user);
        return user;
    }
}