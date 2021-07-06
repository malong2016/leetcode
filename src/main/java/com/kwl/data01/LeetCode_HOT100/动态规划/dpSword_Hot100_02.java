package com.kwl.data01.LeetCode_HOT100.动态规划;

/**
 * @author kuang.weilin
 * @date 2021/7/6 12:38
 */
public class dpSword_Hot100_02 {

    /**
     * 题目1(leetcode 第53题):最大子序和
     * 描述: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * eg: nums = [-2,1,-3,4,-1,2,1,-5,4]  -->连续子数组 [4,-1,2,1] 的和最大，为 6
     * <p>
     * 思路01: 设置一个pre维护,当前数的前序,然后pre+arr[i]和arr[i]比较,取最大值
     * 用max记录最大值
     */
    public int maxSubArray(int[] nums) {   //直接累计
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = pre > 0 ? pre + num : num;
            max = Math.max(max, pre);
        }
        return max;
    }
    public int maxSubArray01(int[] nums) {    //dp法
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 题目02(leetcode 第70题): 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
     *
     * 使用动态规划求解,本题不需要考虑0
     * 思路01: 设置前置pre
     * 思路02: dp[n]
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) return n;   //如果包括0一般就是0 ~ n,不包括从0开始的化就是0 ~ n -1
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    public int climbStairs01(int n) {        //dp方法
        if(n == 1) return 1;
        int[] dp = new int[n];      // n阶楼梯存储到n-1索引的数组下
        dp[0] = 1;
        dp[1] = 2;           //如果是2阶楼梯，有二种方法
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
}
