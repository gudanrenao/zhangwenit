package com.zhangwen.learn.zhangwenit.algorithm.leetcode.util;

import com.zhangwen.learn.zhangwenit.algorithm.leetcode.dto.ListNode;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/6 5:56 PM
 * @Version 1.0
 **/
public class NodeBuilder {

    public static ListNode buildListNode(int ... value){
        assert value.length > 0;
        ListNode head = new ListNode(value[0]);
        ListNode curr = head;
        for (int i = 1; i < value.length; i++) {
            curr.next = new ListNode(value[i]);
            curr = curr.next;
        }

        return head;
    }
}