package com.kwl.data01.algorithm;

/**
 * Memoization(备忘录): 算法记录法
 *
 * @author kuang.weilin
 * @date 2021/2/25
 */
public class Memoization {

    /**
     *使用自顶到下备忘录的动态规划(依旧是要使用递归,不过如果在记忆单元中已经存在就直接返回不需要计算)
     */
    public static int jiechen01(int n) throws Exception {
        if (n<0) throw new Exception("负数不存在阶乘,请重新输入!");          //如果n是负数,就抛出异常
        int[] memory = new int[n+1];        //储存记录功能
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;                  //将所有值设置为-1,表明是全部是没有储存的
        }
        return MemoryTopToBottom(n, memory);
    };
    public static int MemoryTopToBottom(int n,int[] memory) throws Exception {
        if (memory[n]!=-1) return memory[n];        //如果存在,就直接返回,不需要计算
        if (n == 0 || n == 1) {          //递归结束条件
            memory[n] = 1;
        }else {
            memory[n] = n*MemoryTopToBottom(n-1,memory);
        }
        return memory[n];
    }

}
