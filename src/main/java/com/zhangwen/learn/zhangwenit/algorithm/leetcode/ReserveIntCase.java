package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/5/31 11:45 AM
 * @Version 1.0
 **/
public class ReserveIntCase {

    public int reverse(int x) {
        int result = 0;
        int last = 0;
        while(x > 0){
            result = result * 10;
            last = x % 10;
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && last > 7)){
                return 0;
            }
            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && last > 8)){
                return 0;
            }
            result = result + last;
            x = x/10;
        }
        return result;
    }
}