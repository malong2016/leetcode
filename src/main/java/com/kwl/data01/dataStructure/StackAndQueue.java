package com.kwl.data01.dataStructure;

import java.util.*;

/**
 * 单端队列(queue),双端队列(deque)深入理解
 *
 * @author kuang.weilin
 * @date 2020/12/17
 */
public class StackAndQueue {



    /**
     * 1 栈的基本知识(父类list,collection)
     * push('元素'):入栈,返回入栈的value
     * pop():出栈,return出栈的value
     * peek():return栈顶的元素
     * search('元素'):返回指定元素的索引(注意索引是从栈低开始从1计算)
     * isEmpty():判断栈是否是空,return boolean
     * size(): return 栈的长度
     * get(index):获取栈指定Index的值,index是从0始,注意栈底部是Index是0，而栈顶index是stack.size() - 1;
     * iter: java自带的stack,可以使用for.each进行遍历,不会对原stack进行改变
     * 或者可以边出栈和判断空进行遍历
     * 注意: 如果栈为空,pop()和peek()全部会抛出异常
     *
     * 继承的方法(不常用)
     * add('元素'):入栈类似于push,return boolean
     * remove('元素'/index):删除指定元素或者index
     */

    /**
     * 2 Queue基本知识(Queue是一个interface,实现子类LinkedList)  --类比数组,加到尾巴，但是取值是取首值
     * offer('元素'):入队   poll():出队,return出队的元素
     * peek():拿到队首元素,如果不存在就return null
     * isEmpty():判断队列返回为null,  return boolean
     * size(): return 队列的长度
     * remove("元素"):删除指定元素的值
     * 注意: 如果队列为空,poll()和peek()都是返回null,不会抛出异常
     *
     * remove(): 移除头部,等价于poll()         --如果是null,会抛出异常
     * add():添加尾部,等价于offer()
     *
     * remove(元素): 移除制定元素,不能移除索引
     * element():拿到头部
     *
     */


    /**
     * 4 双端队列(Deque):  队首和队尾都可以执行入队和出队的操作  (注意: 也可以当stack栈使用,拥有push/pop方法)
     *
     * * remove(元素): 移除制定元素,不能移除索引
     *
     *
     *模拟stack和普通队列和双端队列
     * 1)  push()/pop(): 模拟stack  push():每次都添加到队列队首!!!pop()就是正常的队首出队  （效果和poll一样）
     * 2) 模拟普通Queue   offer/offerLast   poll()/pollFirst()
     *                   add/addLast        remove()/removeFirst()
     * 3) 模拟双端Queue, offerFirst()/addFirst()    pollLast()/removeLast()        从队首入队，队尾出队
     */

    /**
     * 4 LinkedList:     --更加强大,可以使用双端队列
     * get(index)获取到链表指定的元素
     * remove("元素值"/index)     --索引和元素都可以
     *
     * 5 ArrayList:  只有普通的add()和remove(index/元素) 一般在回溯算法的时候都是使用LinkedList
     */
    public static void main(String[] args) {

        //stack(普通栈)
        Stack<String> stack = new Stack<>();
        //Queue(普通队列)
        Queue<String> queue = new LinkedList<>();
        //Deque(双端队列)
        Deque<String> deque = new LinkedList<>();
        deque.add("张三");
        deque.add("张三01");
        deque.add("张三01");


        //加强版双端队列
//        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.offer("张三");
//        linkedList.offer("张三01");
//        linkedList.offer("张三02");
//        linkedList.remove();
//        System.out.println(linkedList);

    }
}
