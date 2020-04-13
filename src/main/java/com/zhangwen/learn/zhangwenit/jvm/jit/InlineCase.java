package com.zhangwen.learn.zhangwenit.jvm.jit;

/**
 * @Description 内联 编译优化 （配合-XX:+UnlockDiagnosticVMOptions使用）
 * --with-debug-level=fastdebug todo:?
 * -XX:+PrintInlining : 要求虚拟机输出方法内联信息
 * -XX：+PrintCompilation : 要求虚拟机在即时编译时将被编译成本地代码的方法名称打印出来
 * -XX:+PrintAssembly : 输出反汇编信息（配合-XX:+UnlockDiagnosticVMOptions使用）
 * @Author ZWen
 * @Date 2020/4/8 9:58 AM
 * @Version 1.0
 **/
public class InlineCase {

    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        // 这个空循环用于后面演示JIT代码优化过程
        for (int j = 0; j < 100000; j++) ;
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    /**
     * 带有“%”的输出说明是由回边计数器触发的栈上替换编译
     * @param args
     */
    public static void main(String[] args) {
        long result = 0;
        for (int i = 0; i < NUM; i++) {
            result = calcSum();
        }
        System.out.println(result);
    }
}