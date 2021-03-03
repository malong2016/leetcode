package com.kwl.data01.dataStructure;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入理解 java数据结构之堆(heap)
 *
 * @author kuang.weilin
 * @date 2021/2/25
 */
public class Heap {


    /**
     * 定义: heap是一种二叉树的结构-->完全二叉树
     * 最大堆: 每个节点>=孩子节点  最小堆: 每个节点<=孩子节点
     * 搜索: 查看堆顶元素 时间复杂度o(1)
     * 添加: 时间复杂度o(log2n)
     * 删除: 时间复杂度o(log2n)
     * <p>
     * 基本操作:
     * 1 创建堆(最大堆和最小堆)
     * 2 添加元素
     * 3 获取到堆顶元素
     * 4 删除堆顶元素
     * 5 堆的长度
     * 6 堆的遍历
     * <p>
     * leetcode: 215题和692题
     */
    public static void main(String[] args) {
        int a = 10;
        a += 4;    //只要是符号,左右都需要空一格!!!
        //数组是不需要空的 a = a[1]
        int a3[] = new int[2];
        a3[1] = 1;
    }
}
