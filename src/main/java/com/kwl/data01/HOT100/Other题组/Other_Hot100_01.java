package com.kwl.data01.HOT100.Other题组;

/**
 * @author kuang.weilin
 * @date 2021/7/4 0:43
 */
public class Other_Hot100_01 {


    /**
     * 题目01(leetcode 第461题): 汉明距离
     * 描述: 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     * <p>
     * 思路01: 先异或(^)求出的不同不同为就是1，在让x >> 1,测试(x & 1)1的位数
     * 测试集合里面
     */
    public int hammingDistance(int x, int y) {     //注意,这里二个数都是正数
        int cur = x ^ y;
        int res = 0;
        while (cur != 0) {
//            if ((cur & 1) == 1) ++res;
            res += cur & 1;   //如果最后一位为1，就+1,为0就不变
            cur >>= 1;        //也可以/2是有符号右移动
        }
        return res;
    }

    public int hammingDistance01(int x, int y) {
        int res = 0, cur = x ^ y; //不同就是1，那么就求flag中1的个数
        while(cur != 0){
            res++;
            cur = cur & (cur - 1);
        }
        return res;
    }

    /**
     * 题目02(leetcode 第338题): 比特位计数
     * 描述: 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。。
     * eg:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 思路01: 直接求解
     * 思路02: 动态规划  状态转移方程   res[i] = res[i & (i - 1)] + 1 (最后一位去除，在加上1)
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            res[i] = countBit(i);
        }
        return res;
    }
    int countBit(int n){
        int res = 0;
        while(n != 0){
            res++;
            n = n & (n - 1);   //将最后一位变成1
        }
        return res;
    }
    public int[] countBits02(int n) {
        int[] res = new int[n + 1];
        for(int i = 1; i <= n; i++){
            res[i] = res[i & (i - 1)] + 1; // 等于最后一位1去除，在加上1
        }
        return res;
    }

}
