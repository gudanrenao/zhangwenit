package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description 类型转化
 * @Author ZWen
 * @Date 2020/4/1 7:56 PM
 * @Version 1.0
 **/
public class ConvertCase {

    public static void main(String[] args) {
        d2i();
    }

    /**
     * 如果浮点值不是无穷大的话，浮点值使用IEEE 754的向零舍入模式取整，获得整数值v。如果v在目标类型T（int或long）的表示范围之类，那转换结果就是v；否则，将根据v的符号，转换为T所能表示的最大或者最小正数
     */
    public static void d2i() {
        double d = 23232323.56;
        int i = (int) d;
        System.out.println(i);
    }

}