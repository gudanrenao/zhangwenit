package com.zhangwen.learn.zhangwenit.reflect;

import com.google.common.collect.Lists;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/6 4:59 PM
 * @Version 1.0
 **/
public class FirstDemo {

    /**
     * 获取Class对象
     */
    public static void getClassByForName() throws ClassNotFoundException {
        String className = "com.zhangwen.learn.zhangwenit.reflect.FirstDemo";
        Class<?> aClass = Class.forName(className);
        System.out.println(aClass);
    }

    /**
     * 获取Class对象的完整类名和简单类名(不包括包名)
     */
    public static void getClassName() throws ClassNotFoundException {
        Class aClass = FirstDemo.class;
        System.out.println(aClass.getName());
        System.out.println(aClass.getSimpleName());
    }

    /**
     * 获取类的修饰符
     */
    public static void getModifiers() {
        Class aClass = PrivateClassDemo.class;
        System.out.println(aClass.getModifiers());
        System.out.println(Modifier.isFinal(aClass.getModifiers()));
        //b 16进制
        int b = 0x00000010;
        int a = 18 & b;
        System.out.println(a);
    }

    /**
     * 获取包信息
     */
    public static void getPackage() {
        Class aClass = Lists.class;
        Package aPackage = aClass.getPackage();
        System.out.println(aClass.getPackageName());
        System.out.println(aPackage);
    }

    /**
     * 获取父类
     */
    public static void getSuperClass() {
//        Class cl = ArrayList.class;
        Class cl = FirstDemo.class;
        Class superclass = cl.getSuperclass();
        Class superclass1 = superclass.getSuperclass();
        System.out.println(superclass1);
    }

    /**
     * 由于一个类可以实现多个接口，因此 getInterfaces(); 方法返回一个 Class 数组，在 Java 中接口同样有对应的 Class 对象。
     * 注意：getInterfaces() 方法仅仅只返回当前类所实现的接口。当前类的父类如果实现了接口，这些接口是不会在返回的 Class
     * 集合中的，尽管实际上当前类其实已经实现了父类接口。当前类的实现的接口的父接口也不在列表中
     */
    public static void getInterfaces() {
        Class cl = ArrayList.class;
        Class[] interfaces = cl.getInterfaces();
        for (Class anInterface : interfaces) {
            Class[] interfaces1 = anInterface.getInterfaces();
            System.out.println(anInterface);
        }
    }

    /**
     * 获取构造方法列表,注意：对于private类无参构造函数也需要显示声明才能获取到
     * 对于public类，如果没有显示声明构造方法，会默认有一个无参构造函数
     */
    public static void getConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = PrivateClassDemo.class;
        Constructor[] constructors = cl.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        //获取某个构造方法
        Constructor constructor = cl.getConstructor(String.class, int.class);
        Object instance = constructor.newInstance("nihao", 10);
        System.out.println(instance);
    }

    /**
     * 获取构造方法参数列表
     */
    public static void getConstructorParams() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = PrivateClassDemo.class;
        Constructor[] constructors = cl.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.println(parameterType);
            }
            System.out.println("==================================================================");
        }
    }

    /**
     * 获取所有的public方法,包括父类中的public方法
     */
    public static void getMethods() {
        Method[] methods = PrivateClassDemo.class.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
        }
    }

    /**
     * 获取本类中的声明的所有的方法（包括私有方法），但是不包括父类的方法
     */
    public static void getDeclareMethods() {
        Method[] methods = PrivateClassDemo.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
        }
    }

    /**
     * 根据方法名称和方法参数列表获取方法对象
     */
    public static void getMethodByNameAndParams() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cl = PrivateClassDemo.class;
        PrivateClassDemo privateClassDemo = new PrivateClassDemo();
        Method method = cl.getDeclaredMethod("doSomething", int.class);
        //对于私有方法，需要设置执行权限
        if (Modifier.isPrivate(method.getModifiers())) {
            method.setAccessible(true);
        }
//        method.trySetAccessible();
        System.out.println(method);
        System.out.println("参数列表：" + method.getParameterTypes()[0]);
        System.out.println("返回类型：" + method.getReturnType());
        Object invoke = method.invoke(privateClassDemo, 122);
        System.out.println(invoke);
    }

    /**
     * 获取父类私有方法
     */
    public static void getSuperClassPrivateMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = PrivateClassDemo.class;
        Class superclass = cl.getSuperclass();
        Constructor[] constructors = FirstDemo.class.getConstructors();
        Object instance = superclass.getConstructor().newInstance();
        Method method = superclass.getDeclaredMethod("superPrivateMethodTest");
        method.trySetAccessible();
        System.out.println("method is : " + method);
        method.invoke(instance);
    }

    /**
     * 获取静态方法
     */
    public static void getStaticMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class cl = PrivateClassDemo.class;
        Object instance = cl.getConstructor().newInstance();
        Method[] methods = cl.getMethods();
        Method[] declaredMethods = cl.getDeclaredMethods();
        Method staticMethod = cl.getMethod("staticMethodTest", String.class);
        //对于静态方法，invoke第一个参数设为null即可
        staticMethod.invoke(null, "demo");
        //对于私有静态，需要getDeclaredMethod，并setAccessible(true);
        Method staticPrivateMethod = cl.getDeclaredMethod("staticPrivateMethodTest", String.class);
        staticPrivateMethod.trySetAccessible();
        staticPrivateMethod.invoke(null, "privateDemo");
    }

    /**
     * 获取public成员变量，包括父类中的public成员变量
     */
    public static void getFields() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class cl = PrivateClassDemo.class;
        Object instance = cl.getConstructor().newInstance();
        Field[] fields = cl.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field remark = cl.getField("superAge");
        remark.trySetAccessible();
        remark.set(instance, 100);
        System.out.println(instance);

    }

    /**
     * 获取本类中声明的成员变量，不包括父类中成员变量
     */
    public static void getDeclareFields() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = PrivateClassDemo.class;
        Object instance = cl.getConstructor().newInstance();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field remark = cl.getDeclaredField("remark");
        remark.trySetAccessible();
        remark.set(instance, "remarkDemo");
        System.out.println(instance);
    }

    /**
     * 返回类型为泛型对象的
     *
     */
    public static void getReturnTypeWithGeneric() throws NoSuchMethodException {
        Class cl = PrivateClassDemo.class;
        Method method = cl.getDeclaredMethod("getList");
        Type genericReturnType = method.getGenericReturnType();
        if(genericReturnType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                Class actualArgClass = (Class) actualTypeArgument;
                System.out.println("actualArgClass : " + actualArgClass);
            }
            System.out.println("type name :" + parameterizedType.getTypeName());
            Class rawClass = (Class) parameterizedType.getRawType();
            System.out.println("raw class : " + rawClass);
        }
    }

    public static void main(String[] args) throws Exception {
//        getClassByForName();
//        getClassName();
//        getModifiers();
//        getPackage();
//        getSuperClass();
//        getInterfaces();
//        getConstructor();
//        getConstructorParams();
//        getMethods();
//        getDeclareMethods();
//        getMethodByNameAndParams();
//        getSuperClassPrivateMethod();
//        getStaticMethod();
//        getFields();
//        getDeclareFields();
        getReturnTypeWithGeneric();
    }

    private final static class PrivateClassDemo extends SuperClass {

        public String name;
        public int sex;

        private String remark;
        private Integer age;

        public PrivateClassDemo() {
        }

        public PrivateClassDemo(String name) {
            this.name = name;
        }

        public PrivateClassDemo(int sex) {
            this.sex = sex;
        }

        public PrivateClassDemo(String name, int sex) {
            this.name = name;
            this.sex = sex;
        }

        private void privateMethodTest() {
            System.out.println("privateMethodTest");
        }

        public String doSomething() {
            return "success";
        }

        public String doSomething(String msg) {
            return "success:" + msg;
        }

        private void doSomething(int msg) {
            System.out.println("private success : " + msg);
        }

        public static void staticMethodTest(String msg) {
            System.out.println("static msg : " + msg);
        }

        private static void staticPrivateMethodTest(String msg) {
            System.out.println("static private msg : " + msg);
        }

        public List<Integer> getList(){
            return Lists.newArrayList(1,2,3);
        }

        @Override
        public String toString() {
            return "PrivateClassDemo{" +
                    "name='" + name + '\'' +
                    ", sex=" + sex +
                    ", remark='" + remark + '\'' +
                    ", age=" + age +
                    ", superName='" + super.getSuperName() + '\'' +
                    ", superAge=" + superAge +
                    '}';
        }
    }

    private static class SuperClass {

        private String superName;
        public int superAge;

        public SuperClass() {
        }

        public void superPublicMethodTest() {
            System.out.println("super public test");
        }

        private void superPrivateMethodTest() {
            System.out.println("super private test");
        }

        public String getSuperName() {
            return superName;
        }

        public void setSuperName(String superName) {
            this.superName = superName;
        }

        public int getSuperAge() {
            return superAge;
        }

        public void setSuperAge(int superAge) {
            this.superAge = superAge;
        }

        @Override
        public String toString() {
            return "SuperClass{" +
                    "superName='" + superName + '\'' +
                    ", superAge=" + superAge +
                    '}';
        }
    }
}