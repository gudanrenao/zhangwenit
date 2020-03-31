package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description 说明 staticObj、instanceObj、localObj分别存放在哪？
 * 通过 jhsdb 工具
 *
 * 启动参数： -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * @Author ZWen
 * @Date 2020/3/28 5:53 PM
 * @Version 1.0
 **/
public class JHSDB_TestCase {

    static class Test {

        private static ObjectHolder staticObj = new ObjectHolder();

        ObjectHolder instanceObj = new ObjectHolder();

        void foo(){
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("这里打断点");
        }

    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}