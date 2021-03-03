package com.kwl.data01.dataStructure;

//import java.util.Stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 栈(stack)深入理解
 *
 * @author kuang.weilin
 * @date 2020/12/17
 */
public class StackTest {

    /**
     * 定义:栈类似于压子弹和打枪,先入后出!!!!
     *
     * System.out.println(stack);        //打印是:[王二, 张三, 李四]
     * push('元素'):入栈,返回入栈的value      //和队列一样都是可以压入null的!!!!
     * pop():出栈,return出栈的value
     * peek():return栈顶的元素
     * search('元素'):返回指定元素的索引(注意索引是从栈低开始从1计算)
     * isEmpty():判断栈是否是空,return boolean
     * size(): return 栈的长度
     * get(index):获取栈指定Index的值,index是从1开始
     * iter: java自带的stack,可以使用for..each进行遍历,不会对原stack进行改变
     * 或者可以边出栈和判断空进行遍历
     * 注意: 如果栈为空,pop()和peek()全部会抛出异常
     *
     * 继承的方法(不常用)
     * add('元素'):入栈类似于push,return boolean
     * remove('元素'/index):删除指定元素或者index
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("王二");
        stack.push("张三");
        stack.push("李四");
        for (String s : stack) {
            System.out.println(s);
        }
        System.out.println(stack);
    }

}
