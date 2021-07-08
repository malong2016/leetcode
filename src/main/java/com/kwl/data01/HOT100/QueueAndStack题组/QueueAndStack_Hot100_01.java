package com.kwl.data01.HOT100.QueueAndStack题组;

import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/7/4 0:43
 */
public class QueueAndStack_Hot100_01 {
    /**
     * 题目01(leetcode 第32题): 最长有效括号  todo 理解最长有效括号
     * 描述: 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 思路01(my): 利用一个stack,注意底部一定要存放预留一个-1或者最后一个没有被匹配的),以便计算长度
     * 本次入栈都是入index
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i); //栈为空，就把)的index入栈(最后一个没有被匹配到的)
                else res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }

    /**
     * 题目02(leetcode 第155题): 最小栈          --本题和swordOffer 面试题33重合
     * 参考: com.kwl.data01.dataStructure.题目Struct.MinStack
     */
}
