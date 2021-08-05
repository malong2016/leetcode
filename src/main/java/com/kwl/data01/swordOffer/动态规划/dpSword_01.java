package com.kwl.data01.swordOffer.动态规划;

import java.util.Arrays;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class dpSword_01 {

    /**
     * 题目01(swordOffer 第10题-I):求斐波那契数列的第n项
     * 描述: 写一个函数,输入n,求斐波那契(Fibonacci)数列的第n项,斐波那契数列定义如下
     * f(n)={
     * 0   n=0;
     * 1   n=1;
     * f(n-1)+f(n-2) n>1;
     * }
     * 思路01: 传统递归
     * 思路02: 从下到上的动态规划,求f(3),f(4),f(5)....f(n)
     */

    public int fibonacci01(int n) {     //注意:测试需要,要/1000000007求余数
        if (n == 0) return 0;
        if (n == 1) return 1;
        int p1 = 0, p2 = 1;
        for (int i = 2; i <= n; i++) {       //记录一下第一次,临界
            int temp = (p1 + p2) % 1000000007;
            p1 = p2;
            p2 = temp;
        }
        return p2;
    }

    /**
     * 题目02(swordOffer 第10题-II): 青蛙跳台阶问题
     * 描述: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = (a + b) % 1000000007;
            a = b;
            b = temp;
        }
        return b;
    }


    /**
     * 题目03(swordOffer 第19题):正则表达式的匹配        --本题和leetcode 第10题一样
     * 描述: 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];  //注意这里是包括0的的
        dp[0][0] = true;
        for (int i = 2; i < n + 1; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';  //可以消除掉前面一个值
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    //j多余出来二个值,那么就让*把前面哪个值消失掉
                    if (dp[i][j - 2]) dp[i][j] = true;
                        //即让字符 p[j - 2] 多出现 1 次时，能否匹配；
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
                        // 即让字符 '.' 多出现 1 次时，能否匹配；
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 题目04(swordOffer 第49题): 丑数
     * 描述: 我们把只含因子2,3,5叫做丑数。求按从小到大的顺序排序的第1500个丑数。
     * <p>
     * eg:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * <p>
     * 思路01(swordOffer 解法): 先判断这个数是不是丑数,然后遍历1~1500(或者n)是丑数+1,一直到1500 (注意这个在leetcode是超时的)
     * 思路02(leetcode 动态规划):
     */

    public int nthUglyNumber(int n) {           //dp
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];          //存储n个丑数
        dp[0] = 1;                      //第一个丑数是0
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);       //求出最小
            if (dp[i] == n2) a++;     //更新,这里可能有重叠的,不是互斥关系,所以要使用if
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    public int nthUglyNumber01(int n) { //暴力解,超时
        int count = 0;
        int num = 0;
        while (count < n) {  //最后count计数到n就结束
            num++;
            if (isUgly(num)) count++;
        }
        return num;
    }

    boolean isUgly(int number) {              //判断是否是丑数
        if (number <= 0) return false;
        while (number % 2 == 0) number /= 2;
        while (number % 3 == 0) number /= 3;
        while (number % 5 == 0) number /= 5;
        return number == 1;
    }


    /**
     * 题目05(swordOffer 第46题): 把数字翻译成字符串
     * 题目: 给定一个数字 0翻译为'a',1翻译为'b',11翻译为'l',25翻译为'z'
     * 一个数字经常有多个翻译,12258有五种翻译: bccfi,bwfi,bczi,mcfi,mzi
     * 编写一个函数用来计算一个数字有多少种不同的翻译情况
     * <p>
     * 思路01(leetcode 动态规划): f(i) = f(i-2)+f(i-1) 如果Xi-1Xi可以被翻译 f(i) = f(f-1),如果Xi-1Xi不能被翻译
     * 思路02(数字求余): todo等下
     */
    public int translateNu01(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];   //这里要是最后一位对应num,考虑0就是length+1,不考虑就是length
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            String temp = str.substring(i - 2, i);
            if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[str.length()];
    }

    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        if (numStr.length() == 1) return 1;
        int a = 1, b = 1;   //当len长度为0或者是1只有一种翻译情况
        for (int i = 2; i <= numStr.length(); i++) {
            String tempStr = numStr.substring(i - 2, i); //包括前不包括后
            int tempInt = Integer.parseInt(tempStr);
            if (tempInt >= 10 && tempInt <= 25) {
                int temp = b;
                b = a + b;
                a = temp;
            } else {
                a = b;       //b不变,a赋值为b
            }
        }
        return b;
    }

    /**
     * 题目06(swordOffer 第60题): n个骰子的点数
     * 描述: 把n个骰子扔在地上,所有的骰子朝上的一面点数之和为s,输入n,打印出s的所有可能的值出现的概率
     * 补充描述: 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     * <p>
     * eg:  输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     * <p>
     * 情况01: 一维数组的情况
     * 情况02: 二维数组求解
     */
    public double[] dicesProbability(int n) {
        //因为最后的结果只与前一个动态转移数组有关，所以这里只需要设置一个一维的动态转移数组
        //原本dp[i][j]表示的是前i个骰子的点数之和为j的概率，现在只需要最后的状态的数组，所以就只用一个一维数组dp[j]表示n个骰子下每个结果的概率。
        //初始是1个骰子情况下的点数之和情况，就只有6个结果，所以用dp的初始化的size是6个
        double[] dp = new double[6];
        //只有一个数组
        Arrays.fill(dp, 1.0 / 6.0);
        //从第2个骰子开始，这里n表示n个骰子，先从第二个的情况算起，然后再逐步求3个、4个···n个的情况
        //i表示当总共i个骰子时的结果
        for (int i = 2; i <= n; i++) {
            //每次的点数之和范围会有点变化，点数之和的值最大是i*6，最小是i*1，i之前的结果值是不会出现的；
            //比如i=3个骰子时，最小就是3了，不可能是2和1，所以点数之和的值的个数是6*i - i + 1，化简：5 * i + 1
            //当有i个骰子时的点数之和的值数组先假定是temp
            double[] temp = new double[5 * i + 1];
            //从i-1个骰子的点数之和的值数组入手，计算i个骰子的点数之和数组的值
            //先拿i-1个骰子的点数之和数组的第j个值，它所影响的是i个骰子时的temp[j+k]的值
            for (int j = 0; j < dp.length; j++) {
                //比如只有1个骰子时，dp[1]是代表当骰子点数之和为2时的概率，它会对当有2个骰子时的点数之和为3、4、5、6、7、8产生影响，
                // 因为当有一个骰子的值为2时，另一个骰子的值可以为1~6，产生的点数之和相应的就是3~8；比如dp[2]代表点数之和为3，
                // 它会对有2个骰子时的点数之和为4、5、6、7、8、9产生影响；所以k在这里就是对应着第i个骰子出现时可能出现六种情况，
                // 这里可能画一个K神那样的动态规划逆推的图就好理解很多
                for (int k = 0; k < 6; k++) {
                    //这里记得是加上dp数组值与1/6的乘积，1/6是第i个骰子投出某个值的概率
                    temp[j + k] += dp[j] * (1.0 / 6.0);
                }
            }
            //i个骰子的点数之和全都算出来后，要将temp数组移交给dp数组，dp数组就会代表i个骰子时的可能出现的点数之和的概率；用于计算i+1个骰子时的点数之和的概率
            dp = temp;
        }
        return dp;
    }

    /**
     * 题目07(swordOffer 第47):礼物的最大价值
     * 描述: 给定一个二维数组m*n,每一格都放有一个礼物,每一礼物都有一定的价值,
     * 从左下角-->每次向右或者向下移动一格-->到达右下角,求最多能拿到多少价值的礼物?
     * <p>
     * {1,  10,  3,   8},
     * {12,  2,  9,   6},
     * {5,  7,   4,   11},
     * {3,  7,  16,   5}
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);     //上左只能选择一条路
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 题目08(swordOffer 第63题): 股票的最大利润       --本题和leetcode的121题重合
     * 股票价格按照先后顺序放在arr中{9,11,8,5,7,12,16,14} 注意股票是先后时间
     * 只能在时间前买入,后面卖出才能有最大利润,这个股票最大利润是16-5=11。如果没有利润，那么就返回0
     * <p>
     * 思路: 动态规划
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int res = 0, pre = prices[0];        //这里res如果只有一天，就返回0
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, prices[i] - pre);
            pre = Math.min(pre, prices[i]);
        }
        return res;
    }

}
