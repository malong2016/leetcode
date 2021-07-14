package com.kwl.data01.dataStructure.题目Struct;

import java.util.Stack;

/**
 * swordOffer  第09题 - 用两个栈实现队列
 * 描述: 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

 *
 * @author kuang.weilin
 * @date 2021/7/7 1:07
 */
public class CQueue {

    Stack<Integer> stack;
    Stack<Integer> help;         //辅助栈

    public CQueue() {
        stack = new Stack<Integer>();
        help = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stack.push(value);
    }

    public int deleteHead(){
        if (help.isEmpty()){            //一来就辅助栈是否等于null
            while(!stack.isEmpty()){
                help.push(stack.pop());
            }
        }

        //注意这里stack02可能等于null(就是栈01和栈02同时是null)
        return help.isEmpty() ? -1 : help.pop();
    }
}
