package com.kwl.data01.dataStructure.题目Struct;

/**
 * swordOffer 第41题: 数据流中的中位数
 *
 * @author kuang.weilin
 * @date 2021/7/10 23:31
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 题目05(swordOffer 第41题): 数据流中的中位数
 * 描述: 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 例如:
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数
 *
 * 思路01(my): 使用双端链表,注意这里是排序之后中位数，不是直接数组或者链表的中位数。
 * 所以我们使用内置的数组进行排序.超时!!
 * 思路02: 利用大小堆。大顶推是存放较小元素，小顶推是存放较大元素。
 * 小顶堆和大顶堆数量一样或者多一。每次加入元素，先加入到大顶堆，在将大顶推的堆顶(最大元素)放入小顶推。如果
 * 没有保存平衡，就把小顶推的堆顶元素(小顶推的最小元素)放入到大顶推.
 */
public class MedianFinder {


    private PriorityQueue<Integer> big;   //大顶堆
    private PriorityQueue<Integer> small;


    public MedianFinder() {
        this.big = new PriorityQueue<>((o1, o2) -> o2 - o1);    //这是大顶推
        this.small = new PriorityQueue<>();      //默认就是小顶推
    }

    public void addNum(int num) {
        big.offer(num);
        small.offer(big.poll());   //大顶堆的堆顶加入到小顶堆
        if (big.size() + 1 < small.size()) {    //如果不符合平衡
            big.offer(small.poll());      //小顶堆的堆顶加入大顶堆
        }
    }

    public double findMedian() {         //暂时不包括null的问题
        if (big.size() == small.size()) return (big.peek() + small.peek()) / 2.0; //如果二数相等，就取平均
        return small.peek();     //不相等,就是多出来的小顶推的堆顶元素
    }
}
