package com.kwl.data01.dataStructure;

import java.util.*;

/**
 * 单端队列(queue),双端队列(deque)深入理解
 *
 * @author kuang.weilin
 * @date 2020/12/17
 */
public class QueueTest {

    /**
     *    注意: java自带的链式存储,可以使用iter进行遍历1!!
     * 队列(queue):特点是先入先出       队首出队和队尾出队    注意: java里面的普通队列是使用链表来实现的!!!!
     * offer('元素'):入队,return boolean       可以入队为null
     * poll():出队,return出队的元素           注意:如果队为null,出队不会报错,返回null
     * peek():拿到队首元素,如果不存在就return null
     * isEmpty():判断队列返回为null,  return boolean
     * size(): return 队列的长度
     * remove("元素"):删除指定元素的值
     * 注意: 如果队列为空,poll()和peek()都是返回null,不会抛出异常
     *
     * 出现异常就抛出异常(继承父类的方法)
     * remove():继承Collection,移除头部
     * add():添加尾部
     * element():拿到头部
     *
     * 双端队列(Deque):  队首和队尾都可以执行入队和出队的操作  (也可以当stack栈使用,拥有push/pop方法)
     * addLast():从对尾添加元素,也是添加元素默认的行为  等于于add()
     * addFirst(): 从对首添加元素。
     *
     * removeLast():从双端队列的队尾删除元素
     * removeFirst(): 从双端队列的队首删除元素,也是默认的删除方法!!  等价于remove()
     * remove("元素值") :删除指定元素,注意不能删除指定索引!!!
     *
     * getFrist()/getLast(): 获取队列头或者尾部的元素
     *
     * LinkedList:
     *   get(index)获取到链表指定的元素
     *   remove("元素值"/index):删除指定index的值或者制定元素值!!!
     *   push()/pop(): 模拟stack  push():每次都添加到队列队首!!!pop()就是正常的出队
     */
    public static void main(String[] args) {

//        Queue<String> queue = new LinkedList<>();
//        queue.offer(null);
//        if (queue.poll() == null) {
//            System.out.println("出队为null");
//        }else {
//            System.out.println("jdjsk");
//        }

        //双端队列,可以从头部或者尾部进行删除操作
//        Deque<String> deque = new LinkedList<>();
//        deque.offer("张三");
//        deque.offer("张三");
//        Stack<String> stack = new Stack<>();
    }
}
