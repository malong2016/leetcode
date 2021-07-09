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
     * search('元素'):返回指定元素的索引(注意索引是从栈顶开始向下计算)
     * isEmpty():判断栈是否是空,return boolean
     * size(): return 栈的长度
     * get(index): 获取栈指定Index的值,栈底index是0
     *
     * 注意: 如果栈为空,pop()和peek()全部会抛出异常
     *
     * 继承的方法(不常用)
     * add('元素'):入栈类似于push,return boolean
     * remove('元素'/index):删除指定元素或者index
     */

    /**
     * 2 Queue基本知识(Queue是一个interface,实现子类LinkedList)  --类比数组,加到尾巴，但是取值是取首值
     * offer('元素'):入队
     * poll():出队,return出队的元素
     * peek():拿到队首元素
     * isEmpty():判断队列返回为null,  return boolean
     * size(): return 队列的长度
     * remove("元素"):删除指定元素的值
     * 注意: if队列为空,poll()和peek()都是返回null,不会抛出异常
     *
     * remove(): 移除头部,等价于poll()         --if是null,会抛出异常
     * add():添加尾部,等价于offer()
     *
     * remove(元素): 移除制定元素,不能移除索引
     * element():拿到头部                   --如果没有头部,抛出异常
     */


    /**
     * 4 双端队列(Deque):  队首和队尾都可以执行入队和出队的操作
     *
     * 模拟stack和普通Queue和双端Queue
     *     1) 模拟stack:                             push():每次都添加到队列队首   pop(): 正常的队首出队  （效果和poll()一样）
     *     2) 模拟普通Queue(队首出队，队尾入队):        offer/offerLast   poll()/pollFirst()
     *                                               add/addLast        remove()/removeFirst()
     *     3) 模拟双端Queue(队首入队，队尾出队):        offerFirst()/addFirst()    pollLast()/removeLast()
     */


    /**
     * 5 LinkedList:     --更加强大,可以使用双端队列
     * get(index)获取到链表指定的元素
     * remove("元素值"/index)     --索引和元素都可以
     */

    /**
     * 6 ArrayList:  只有普通的add()和remove(index/元素) 一般在回溯算法的时候都是使用LinkedList
     */

    /**
     * 7 优先队列（PriorityQueue）： 默认是建立最小堆
     *  如果出队，那么会重新进行堆排序，堆顶元素还是最小(最大)值
     *
     */
    public static void main(String[] args) {

        //stack(普通栈)
        Stack<String> stack = new Stack<>();


        //Queue(普通队列)
        Queue<String> queue = new LinkedList<>();


        //Deque(双端队列)
        Deque<String> deque = new LinkedList<>();

        //LinkedList(加强版双端队列)
        LinkedList<String> linkedList = new LinkedList<>();

        //数组类型List
        ArrayList<String> arrayList = new ArrayList<>();

        //优先队列PriorityQueue,默认是最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {     //传入比较器，可以改变比较的规则
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
                return o1 - o2;
            }
        });
    }
}
