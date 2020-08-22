package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/4 6:41 PM
 * @Version 1.0
 **/
public class Case14 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(args));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        int index = 0;
        for (; index < strs[0].length(); index++) {
            char curr = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() <= index || strs[i].charAt(index) != curr) {
                    return strs[0].substring(0, index);
                }
            }
        }
        return strs[0].substring(0, index);
    }
}