package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

import com.zhangwen.learn.zhangwenit.algorithm.leetcode.dto.ListNode;
import com.zhangwen.learn.zhangwenit.algorithm.leetcode.util.NodeBuilder;

/**
 * @Description
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * @Author ZWen
 * @Date 2020/6/6 5:55 PM
 * @Version 1.0
 **/
public class Case234 {

    public static void main(String[] args) {
        ListNode head = NodeBuilder.buildListNode(-1,3,4, -1);
//        System.out.println(head);
//        System.out.println(isPalindrome(head));

        System.out.println(isPalindrome2(head));
    }


    public static boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        //首先通过快慢指针找到前半部分的尾结点
        ListNode endOfFirstHalf = endOfFirstHalf(head);
        //反转后半部分节点,并返回后半部分节点的首节点，以便后面再反转回去
        ListNode startOfSecondHalf = reverse(endOfFirstHalf.next);

        ListNode p1 = head;
        ListNode p2 = startOfSecondHalf;
        boolean result = true;
        //循环比较
        while(result && p2 != null){
            if(p2.val != p1.val){
                result = false;
            }
            p2 = p2.next;
            p1 = p1.next;
        }
        //将反转的复原
        endOfFirstHalf.next = reverse(startOfSecondHalf);
        return result;
    }

    public static ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static ListNode endOfFirstHalf(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //个人方案，但是空间复杂度为O(n)
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        //首先找到尾结点
        ListNode tail = head;
        int length = 1;
        while(tail.next != null){
            tail = tail.next;
            length++;
        }
        //将后面 length/2个放入数组中 .这里需要O(n)空间复杂度了。。。
        int middle = length / 2;
        int[] vals = new int[middle];
        //将后 length/2 个链接元素放在数组中
        tail = head;
        int start = middle + length % 2;
        length = 1;
        while(tail != null){
            if(length > start){
                vals[length - start - 1] = tail.val;
            }
            length++;
            tail = tail.next;
        }
        //循环 length/2次，头结点和尾结点都相等
        for(int i = 0; i < middle;i++){
            if(head.val != vals[middle - i - 1]){
                return false;
            }
            head = head.next;
        }
        return true;
    }

}