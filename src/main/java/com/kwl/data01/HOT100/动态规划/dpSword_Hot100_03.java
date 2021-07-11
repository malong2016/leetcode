package com.kwl.data01.HOT100.动态规划;

import java.util.Map;

/**
 * @author kuang.weilin
 * @date 2021/7/7 22:21
 */
public class dpSword_Hot100_03 {

    /**
     * 题目01(leetcode 第279题): 完全平方数
     * 描述:给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     * <p>
     * eg：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * <p>
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     * <p>
     * 思路01:  初始化是dp[0] = 0,dp[1]代表就是输入1
     * 动态规划，dp[i - j*j] + 1(注意,此时是j*j构成完全平方数,所以就是要dp+1)
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];    //我们初始化包括dp[0] = 0,dp[16 - 4*4]+1=dp[0]+1,正好是1(不要考虑0*0!!!!)
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;                   //dp[i]初始化,最大值是1*1 + 1*1 ....
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.max(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 题目02(leetcode 第312题): 戳气球
     * 描述: 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     *  这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * 求所能获得硬币的最大数量。
     * <p>
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     * <p>
     * 思路: 动态规划 dp[i,j] 开区间，代表i+1,j-1见搓破气球的最大值(因为要靠考虑周围的值)
     */
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len + 2];    //temp左右二端要加入1
        int[][] dp = new int[len + 2][len + 2];    //dp记录开区间
        for (int i = 0; i < nums.length; i++) {
            temp[i + 1] = nums[i];
        }
        temp[0] = temp[len + 1] = 1;              //首尾设置为1
        for (int i = len - 1; i >= 0; i--) {      //控制最左端,起始值是到3
            for (int j = i + 2; j <= len + 1; j++) {    //控制最右端
                for (int k = i + 1; k < j; k++) {     //在中间走
                    int sum = temp[i] * temp[k] * temp[j] + dp[i][k] + dp[k][j];    //这个是本次的值,初始值把k戳破,还是会累乘
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][len + 1];
    }

    /**
     * 题目03(leetcode 第10题): 正则表达式匹配    --本题和swordOffer 第19题 一样
     * 描述: 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
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
     * 题目04(leetcode 第309题): 最佳买卖股票时机含冷冻期
     * 描述: 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。。
     * <p>
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 思路: f[i][0]: 手上持有股票的最大收益  (昨天就有股票or今天买入)
     * f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益    (今天卖出)
     * f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益  (今天一开始就没有股票)
     *
     * 初始化:   dp[0][0] = -prices[0];
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 1) return 0;
        //1是持有股票(昨天就有股票or今天买入)2是不持有股票，在冷冻期(今天卖出),3是不持有股票，也不在冷冻期(今天一开始就没有股票)
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];    //第一天买入直接是-收益，dp[0][0]初始化为0
        for (int i = 1; i < len; i++) {
            //如果在当天持有股票.说明是 1 昨天持有股票 2 昨天不持有，而且不处于冷冻期。今天买入！！！
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //不持有股票，而且在冷冻期，说明是 1 今天卖出，那么肯定是昨天持有
            dp[i][1] = dp[i - 1][0] + prices[i];
            //现在不持有股票,不在冷冻期(今天开始就没有股票)。说明是 1 昨天不持有，在冷冻期 2 昨天不持有，不在冷冻期
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);     //注意dp[len-1][0]持有股票是没有意义的
    }
}
