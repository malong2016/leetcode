package com.kwl.data01.HOT100.Other题组;

/**
 * @author kuang.weilin
 * @date 2021/7/4 0:43
 */
public class Other_Hot100_01 {


    /**
     * 题目01(leetcode 第462题): 汉明距离
     * 描述: 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     *
     * 思路01: 先异或(^)求出的不同不同为就是1，在让x >> 1,测试(x & 1)1的位数
     * 测试集合里面
     */
    public int hammingDistance(int x, int y) {     //注意,这里二个数都是正数
        int cur = x ^ y;
        int res = 0;
        while (cur != 0) {
            if ((cur & 1) == 1) ++res;
            cur >>= 1;        //也可以/2是有符号右移动
        }
        return res;
    }
}
