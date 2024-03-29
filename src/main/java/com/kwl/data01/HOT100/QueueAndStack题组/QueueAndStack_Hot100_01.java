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
                else res = Math.max(res, i - stack.peek());   //注意和上面的是排斥关系!!!
            }
        }
        return res;
    }

    /**
     * 题目02(leetcode 第155题): 最小栈          --本题和swordOffer 面试题33重合
     * 参考: com.kwl.data01.dataStructure.题目Struct.MinStack
     */

    /**
     * 题目03(leetcode 第146题): LRU 缓存机制
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * 代码参考: com.kwl.data01.dataStructure.题目Struct.LRUCache
     *
     * 思路01:
     */
}
