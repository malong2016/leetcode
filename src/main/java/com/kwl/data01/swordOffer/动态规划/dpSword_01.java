package com.kwl.data01.swordOffer.动态规划;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class dpSword_01 {
    /**
     * 题目1(swordOffer 面试题14):剪绳子
     * 描述: 给你一根长度为n的绳子,请把绳子剪成m段(m和n都是整数 n>1 m>1)
     * 长度计算为k[0],k[1],k[2]...k[m]请问k[0]*k[1]*k[2]...k[m]最大是多少
     * eg:当绳子是8的时候,剪成2,3,3的三段,此时得到最大的乘积是18
     * <p>
     * 思路01(动态规划法):从下到上进行计算,先计算到f(1)max(绳子长度为1的最大值),f(2)max...f(length)max
     * f(length)max = f(i)max*f(length-i)max
     * 思路02(贪婪法):
     */
    public int maxProductAfterCutting01(int length) {        //思路01,使用动态规划求解!!!
        int[] dp = new int[length + 1];    //存储最大值
        dp[2] = 1;     //绳子是2的时候,剪1刀是1*1 = 1;
        for (int i = 3; i < length + 1; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[length];
    }

    /**
     * 题目2(swordOffer 面试题10题目一):求斐波那契数列的第n项
     * 描述: 写一个函数,输入n,求斐波那契(Fibonacci)数列的第n项,斐波那契数列定义如下
     * f(n)={
     * 0   n=0;
     * 1   n=1;
     * f(n-1)+f(n-2) n>1;
     * }
     * 思路01: 传统递归
     * 思路02: 从下到上的动态规划,求f(3),f(4),f(5)....f(n)
     */
    public static long fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int lastAndLast = 0;  //上一个数的上一个 n-2
        int last = 1;      //上一个数n-1
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastAndLast;
            lastAndLast = last;
            last = now;
        }
        return now;
    }

    public int fibonacci01(int n) {     //注意:测试需要,要/1000000007求余数
        //我们计算cur和next二个值
        int  cur = 0,next = 1;
        for (int i = 0; i < n; i++) {         //传入n就要计算几次,也就是最后a的值
            int   temp = (cur + next)% 1000000007;    //记录cur下一个值
            cur = next;            //二个指针向左移动
            next = temp;
        }
        return cur;
    }



}
