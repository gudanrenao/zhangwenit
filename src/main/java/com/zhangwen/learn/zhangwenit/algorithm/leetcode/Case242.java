package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 * @Author ZWen
 * @Date 2020/6/3 2:11 PM
 * @Version 1.0
 **/
public class Case242 {

    public boolean isAnagram(String s, String t) {
        int length = s.length();
        if (length != t.length()) {
            return false;
        }
        //只包含小写字母，可以使用大小为26的数组存储每个字母出现的次数，数组下标使用 char - 26
        int[] chars = new int[26];
        for (int i = 0; i < length; i++) {
            chars[s.charAt(i) - 26]++;
            chars[t.charAt(i) - 26]--;
        }
        //如果有数组元素不为0，表示不是字母异位词
        for (int v : chars) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}