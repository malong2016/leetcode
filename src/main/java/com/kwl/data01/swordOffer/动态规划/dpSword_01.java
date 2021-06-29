package com.kwl.data01.swordOffer.动态规划;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class dpSword_01 {
    /**
     * 题目1(swordOffer 面试题14):剪绳子I
     * 描述: 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
     *
     *
     * 思路01(动态规划法):从下到上进行计算,先计算到f(1)max(绳子长度为1的最大值),f(2)max...f(length)max
     * 状态转移方程式: f(length)max = f(i)max*f(length-i)max
     */
    public int maxProductAfterCutting01(int n) {        //思路01,使用动态规划求解!!!
        if (n <= 3) return n -1;  //3 = 2 * 1,2 = 1*1;
        int[] dp = new int[n + 1];
        dp[1] = 1;          //因为如果大于4，不分割1,2，3是更大的，这三种情况要分开讨论
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < dp.length; i++) {
            // for (int j = 1; j < i; j++) {
            for (int j = 1; j <= i/2; j++) { //优化，比较前半段就可以了
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }
    

    /**
     * 题目3(swordOffer 面试题10题目一):求斐波那契数列的第n项
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
        if (n == 0 || n == 1)return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {   //第一次和最后一次临界
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public static int fibonacci01(int n) {     //注意:测试需要,要/1000000007求余数
        if (n == 0) return 0;
        if (n == 1) return 1;
        int p1 = 0, p2 = 1;
        for (int i = 2; i <= n; i++) {       //记录一下第一次,临界
            int temp = (p1 + p2) % 1000000007;
            p1 = p2;
            p2 = temp;
        }
        return p2 ;
    }


    public static void main(String[] args) {
        System.out.println(fibonacci(48));
    }
}
