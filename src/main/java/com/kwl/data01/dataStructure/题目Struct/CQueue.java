package com.kwl.data01.dataStructure.题目Struct;

import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/7/7 1:07
 */
public class CQueue {          //剑指 Offer 09. 用两个栈实现队列

    Stack<Integer> stack01;
    Stack<Integer> stack02;

    public CQueue() {
        stack01 = new Stack<Integer>();       //栈01是入队栈
        stack02 = new Stack<Integer>();         //栈02是出队栈
    }

    public void appendTail(int value) {
        stack01.push(value);
    }

    public int deleteHead(){
        if (stack02.isEmpty()){            //一来就判断栈02是否等于null
            while(!stack01.isEmpty()){
                stack02.push(stack01.pop());
            }
        }

        //注意这里stack02可能等于null(就是栈01和栈02同时是null)
        return stack02.isEmpty() ? -1 : stack02.pop();
        // if (stack02.isEmpty())     return -1;
        // return (int)stack02.pop();
    }
}
