package com.kwl.data01.swordOffer.Other题组;

/**
 * 其他题组1
 *
 * @author kuang.weilin
 * @date 2021/2/18
 */
public class swordOther_01 {


    /**
     * 题目1(swordOffer 面试题15): 二进制中1的个数
     * 描述: 请实现一个函数,输入一个整数,输出该数二进制表示中1的个数
     * 例如: 把9表示成二进制1001,有2位是1.因此，如果输入是9，该函数输出是2
     * <p>
     * 思路01(swordOffer): 设置一个1不断左移动和1,10,100和传入的n进行比较
     * 思路02: (n-1)&n可以吧最右边的1变成0
     */
    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;           //每次进行n-1&n都是把最右边的1变成0
        }
        return count;
    }

    public static int numberOf2(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & n) != 0) count++;      //注意比较运算符优先级大于&位运算符!!!
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 题目2(swordOffer 面试题49):丑数
     * 描述: 我们把只含因子2,3,5叫做丑数。求按从小到大的顺序排序的第1500个丑数。
     * 例如: 6,8是丑数,但是14不是丑数(包含因子7).习惯我们把1也当做丑数
     * <p>
     * 思路01(swordOffer 解法): 先判断这个数是不是丑数,然后遍历1~1500(或者n)是丑数+1,一直到1500
     * 思路02(leetcode 动态规划):  todo 输入理解
     */
    public static boolean isUgly(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }

    public static int getUglyNumber(int index) {     //获取第index个丑数
        int number = 0;
        for (int i = 0; i < index; ) {
            number++;
            if (isUgly(number)) i++;     //如果是丑数是i++,最后加到1500次,此时就可以输出
        }
        return number;
    }

    public static int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    /**
     * 题目3(swordOffer 面试题43):1~n整数中1出现的次数
     * 描述: 输入一个整数n,1~n这个n整数的十进制中1出现的次数
     * eg: 输入12,那么1~12这些整数中包含1,10,11和12一共出现了5次
     * <p>
     * 思路01(swordOffer 暴力解): n%10==1,个位存在1,如果大于10,n/=10在进行判断
     */
    public static int numberOf1_my(int n) {
        int number = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i; //不能改变本身
            while (temp != 0) {
                if (temp % 10 == 1) number++;
                temp /= 10;
            }
        }
        return number;
    }


    /**
     * 题目4(swordOffer 面试题60): n个骰子的点数
     * 描述: 把n个骰子扔在地上,所有的骰子朝上的一面点数之和为s,输入n,打印出s的所有可能的值出现的概率
     * 思路01: todo 先系统学习动态规划在来做!!
     */
    public double[] dicesProbability(int n) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1_my(12));
    }
}
