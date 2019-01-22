package com.zhangwen.learn.zhangwenit.designmodel.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/15 5:39 PM
 * @Version 1.0
 **/
public class CarProxy implements MethodInterceptor {

    public Object getProxy(Class classObj) {
        Enhancer enhancer = new Enhancer();
        //设置生成的class的父类，就是告诉cglib,生成的子类需要继承哪个类
        enhancer.setSuperclass(classObj);
        //设置回调
        enhancer.setCallback(this);
        //1.生成源代码
        //2.编译为class文件
        //3.加载到JVM中，并返回被代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CarProxy before ...");
        methodProxy.invokeSuper(o, objects);
        System.out.println("CarProxy after ...");
        return null;
    }
}