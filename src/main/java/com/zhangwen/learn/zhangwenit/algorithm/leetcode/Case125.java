package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

/**
 * @Description 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @Author ZWen
 * @Date 2020/6/3 3:59 PM
 * @Version 1.0
 **/
public class Case125 {

    public static void main(String[] args) {
        String input = "race a car";
        System.out.println(isPalindrome(input));

    }

    //双指针
    public static boolean isPalindrome(String s) {
        int head = 0,tail = s.length() - 1;
        while(head < tail){
            char headC = s.charAt(head);
            char tailC = s.charAt(tail);
            //变大写
            if(headC >= 'a' && headC <= 'z'){
                headC = (char)(headC - 32);
            }
            if(tailC >= 'a' && tailC <= 'z'){
                tailC = (char)(tailC - 32);
            }
            if(!((headC >= '0' && headC <= '9') || (headC >= 'A' && headC <= 'Z'))){
                head++;
                continue;
            }
            if(!((tailC >= '0' && tailC <= '9') || (tailC >= 'A' && tailC <= 'Z'))){
                tail--;
                continue;
            }
            if(headC != tailC){
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}