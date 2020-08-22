package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description 字符串转换整数
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 * @Author ZWen
 * @Date 2020/6/3 5:42 PM
 * @Version 1.0
 **/
public class Case67 {

    public static void main(String[] args) {
        String s = "  -0012a42";
        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String str) {
        str = str.trim();
        int length = str.length();
        if(length == 0){
            return 0;
        }
        char first = str.charAt(0);
        int result = 0,sign = 1;
        if(first == '-'){
            sign = -1;
        } else if(first == '+'){
            result = 0;
        } else if(!(first >= '0' && first <= '9')){
            return 0;
        } else {
            result = first - 48;
        }
        int value = 0;
        for(int i = 1;i < length;i++){
            char curr = str.charAt(i);
            if(!(curr >= '0' && curr <= '9')){
                return result * sign;
            }
            value = curr - 48;
            if((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && value > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + value;
        }
        return result * sign;
    }
}