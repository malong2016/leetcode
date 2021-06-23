package com.kwl.data01.algorithm;

/**
 * DynamicProgramming(动态规划) 深入理解
 * @author kuang.weilin
 * @date 2021/2/25
 */
public class DynamicProgramming {


    /**
     * DynamicProgramming(动态规划) -- 最优子结构，能把问题转化为子问题，一步一步推到最终的答案
     * DP的三要素: 1)初始状态 2)方程式 3)终止状态
     * dp有点类似我们的数学归纳法(记忆功能)步骤如下
     * 1 定义数组的含义(一维数组或者二维数组)
     * 2 找出状态转移方程式，也是数组之间的关系  --最后一步找
     * 3 找出初始值
     * 以斐波拉数列为列子:
     *         初始状态:  f(0)和f(1)
     *         中间状态: f(2),f(3)....f(n-1)
     *         终止状态:  f(n)
     *         状态转移: 从已经得到的值,来求当前状态上来
     *         状态转移方程式:    f(n) = f(n-1) + f(n-2)
     * 常见的题目涉及题目(机器人路径swordOffer):
     *        1) 有多少种方法或者方式:  机器人从左上角到右下角右多少个路径
     *        2) 求最值: 最大值和最小值
     *              机器人大左到右路径最大数字和
     *        3)求存在性: 是否存在某个肯
     *        是否存在机器人从左到右的路径
     *  常见的leetcode题目: 509/62/121/70/279
     */


    /**
     * 1 传统的递归
     */
    public static int jiechen(int n) throws Exception {
        if (n<0) throw new Exception("负数不存在阶乘,请重新输入!");          //如果n是负数,就抛出异常
        if (n==1 || n==0) return 1;    //这是递归的出口
        return n * jiechen(n - 1);         //如果大于1,就递归
    };

    /**
     * 2 自低向上进行动态规划(避免直接使用递归的重复计算)
     */
    public static int jieMemory01(int n) throws Exception{
        if (n<0) throw new Exception("负数不存在阶乘,请重新输入!");          //如果n是负数,就抛出异常
        int[] memory = new int[n+1];        //储存记录功能
        memory[0] = 1;          //0!和1!为1,储存到记忆功能之中
        memory[1] = 1;
        //依次计算memory[2]、memory[3]....memory[n](如果直接使用递归要不断的重复计算,浪费时间)
        for (int i = 2; i <= n; i++) {
            memory[i] = i * memory[i - 1];
        }
        return memory[n];
    };



    /**
     *3 使用自顶到下备忘录的动态规划(依旧是要使用递归,不过如果在记忆单元中已经存在就直接返回不需要计算)
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

    public static void main(String[] args) throws Exception {
        //        System.out.println(jiechen(4));
//        System.out.println(jieMemory01(4));
        System.out.println(jiechen01(4));
    }
}
