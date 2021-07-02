package com.kwl.data01.dataStructure.题目Struct;

import java.util.LinkedList;

/**
 * swordOffer  第59题 - II. 队列的最大值
 *
 * 思路01: 直接遍历队列，每次都求出最大值
 * 思路02：设置二个双端队列，一个记录每次pop的最大值，利用滑动窗口
 * @author kuang.weilin
 * @date 2021/7/1 22:48
 */
public class MaxQueue {    //这个解释滑动窗口的解答

    LinkedList<Integer> queue;
    LinkedList<Integer> help;         //保持开头是最大的,这是辅助队列


    public MaxQueue() {
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public int max_value() {
        if (help.isEmpty()) return -1;
        return help.peekFirst();      //队首最大
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!help.isEmpty() && help.peekLast() < value)    //这里是把比value小的数全部清除
            help.pollLast();
        help.offer(value);
    }

    public int pop_front() {      //出队
        if (queue.isEmpty()) return -1;
        Integer pollValue = queue.poll();
        if (pollValue.equals(help.peekFirst())) help.pollFirst();      //如果出队队列和maxQueue是相等,那么就出队
        return pollValue;
    }
}
