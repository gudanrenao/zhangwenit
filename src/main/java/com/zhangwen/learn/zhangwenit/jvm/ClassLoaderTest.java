package com.zhangwen.learn.zhangwenit.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 对于任意一个类，都必须由加载它的类加载器和这个类本身一起共同确立其在Java虚拟机中的唯一性，每一个类加载器，都拥有一个独立的类名称空间
 * @Author ZWen
 * @Date 2020/4/3 9:49 AM
 * @Version 1.0
 **/
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream in = getClass().getResourceAsStream(fileName);
                    if (in == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Class<?> loadClass = myClassLoader.loadClass("com.zhangwen.learn.zhangwenit.jvm.ClassLoaderTest");
        Object instance = loadClass.newInstance();
        System.out.println(instance.getClass());
        System.out.println(instance instanceof ClassLoaderTest);

        //所有在获取启动类加载器的场景（譬如Object.class.getClassLoader()）中仍然会返回null来代替
        ClassLoader bootClassLoader = Object.class.getClassLoader();
        System.out.println(bootClassLoader);
    }
}