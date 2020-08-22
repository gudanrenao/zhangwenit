package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description  外观数列
 * https://leetcode-cn.com/problems/count-and-say/
 * @Author ZWen
 * @Date 2020/6/4 6:05 PM
 * @Version 1.0
 **/
public class Case38 {

    public static void main(String[] args) {
        System.out.println(countAndSay(2));
    }

    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");

        for(int i = 1;i < n; i++){
            int size = sb.length();
            char last = sb.charAt(0);
            char curr;
            int times = 1;
            for(int j = 1;j < size;j++){
                curr = sb.charAt(j);
                if(last == curr){
                    times++;
                } else {
                    sb.append(times).append(last);
                    last = curr;
                    times = 1;
                }
            }
            sb.append(times).append(last);
            sb.delete(0,size);
        }

        return sb.toString();
    }
}