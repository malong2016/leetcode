package com.kwl.data01.dataStructure.题目Struct;

import java.util.Stack;

/**
 * leetcode 面试题155  (最小栈)    --不用靠率栈为null
 * swordOffer 面试题33 (包含min函数的栈)
 *
 * @author kuang.weilin
 * @date 2021/7/4 0:17
 */
public class MinStack {        //设置一个辅助栈

    Stack<Integer> stack;
    Stack<Integer> help;

    public MinStack() {
        this.stack = new Stack<>();
        this.help = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (help.isEmpty()) help.push(val);              //这里是help,注意
        else help.push(Math.min(help.peek(), val));
    }

    public void pop() {         //本题目在leetcode不需要考虑异常
        stack.pop();
        help.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return help.peek();
    }
}
