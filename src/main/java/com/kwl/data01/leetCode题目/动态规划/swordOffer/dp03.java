package com.kwl.data01.leetCode题目.动态规划.swordOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/2/27
 */
public class dp03 {
    /**
     * 题目1: 单词拆分
     * 描述: 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明: 拆分时可以重复使用字典中的单词。你可以假设字典中没有重复的单词。
     * eg: s = "applepenapple", wordDict = ["apple", "pen"] --> true
     * s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] --> false
     * <p>
     * 思路01(动态规划): 先将所有的词加入到HashSet,
     * 状态转移方程: dp[i]=dp[j] && check(s[j..i−1]) 一当成立就结束掉循环,接着继续加
     * 边界dp[0] = true
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hashSet = new HashSet<>(wordDict);  //注意,可以传入Collection的子类!!!
        boolean[] dp = new boolean[s.length() + 1];     //dp[0]为true
        dp[0] = true;
        for (int j = 1; j <= s.length(); j++) {   //注意不包括j
            for (int i = 0; i < j; i++) {
                if (dp[i] && hashSet.contains(s.substring(i, j))) {  //一定要设置边界dp[i] = true
                    dp[j] = true;
                    break;         //有一个为true,就结束掉
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 题目2(leecode 300题): 最长递增子序列
     * 描述: 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。子序列是由数组派生而来的序列，
     * 删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * eg: 输入：nums = [10,9,2,5,3,7,101,18] --> 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
     *
     * 思路01(动态规划):dp[i]代表nums前i个数字的最长子序列
     * 转移方程dp[i] = max(dp[i], dp[j] + 1)
     * 思路02(动态规划+二分查找): todo
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 1;     //初始化结果的返回值
        Arrays.fill(dp, 1);     //将数组所有都初始化为0
        for (int i = 0; i < dp.length; i++) {   //注意i是在右边,dp所要存储的每一个
            for (int j = 0; j < i; j++) {    //遍历i左边的数字,每个都要遍历,来dp[i]求出最大值
                if(nums[i]>nums[j]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            res = Math.max(dp[i], res);   //最大值进行替换
        }
        return res;
    }


}
