package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

import com.zhangwen.learn.zhangwenit.algorithm.leetcode.dto.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description 验证对称二叉树 https://leetcode-cn.com/problems/symmetric-tree/
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *  
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * @Author ZWen
 * @Date 2020/6/14 5:52 PM
 * @Version 1.0
 **/
public class Case101 {


    //迭代 List 简化
    public static boolean isSymmetric8(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        while (!queue.isEmpty()) {
            leftNode = queue.poll();
            rightNode = queue.poll();
            if(leftNode == null && rightNode == null){
                continue;
            }
            if ((leftNode == null ||  rightNode == null) || leftNode.val != rightNode.val) {
                return false;
            }
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }
        return true;
    }

    //迭代 Stack 简化
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        while (!stack.isEmpty()) {
            leftNode = stack.pop();
            rightNode = stack.pop();
            if(leftNode == null && rightNode == null){
                continue;
            }
            if ((leftNode == null ||  rightNode == null) || leftNode.val != rightNode.val) {
                return false;
            }
            stack.push(leftNode.left);
            stack.push(rightNode.right);
            stack.push(leftNode.right);
            stack.push(rightNode.left);
        }
        return true;
    }

    //迭代
    public static boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        leftStack.push(root.left);
        rightStack.push(root.right);
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        while (!leftStack.isEmpty()) {
            leftNode = leftStack.pop();
            rightNode = rightStack.pop();
            if (leftNode.val != rightNode.val) {
                return false;
            }
            if (leftNode.left != null && rightNode.right != null) {
                leftStack.push(leftNode.left);
                rightStack.push(rightNode.right);
            } else if (!(leftNode.left == null && rightNode.right == null)) {
                return false;
            }
            if (leftNode.right != null && rightNode.left != null) {
                leftStack.push(leftNode.right);
                rightStack.push(rightNode.left);
            } else if (!(leftNode.right == null && rightNode.left == null)) {
                return false;
            }
        }
        return true;
    }

      //递归
     public boolean isSymmetric3(TreeNode root) {
         if(root == null) {
             return true;
         }
         return isSymmetric4(root.left,root.right);
     }

     public boolean isSymmetric4(TreeNode left,TreeNode right){
         if(left == null && right == null){
             return true;
         }
         if(left == null || right == null) {
             return false;
         }
         if(left.val != right.val){
             return false;
         }
         return isSymmetric4(left.left,right.right) && isSymmetric4(left.right,right.left);
     }
}