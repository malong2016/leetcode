package com.kwl.data01.LeetCode_01.Arrays题组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kuang.weilin
 * @date 2021/7/8 22:43
 */
public class Arrays_Hot100_01 {

    /**
     * 题目01(leetcode 第209题): 长度最小的子数组
     * 描述: 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 思路01: 滑动窗口
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, res = nums.length + 1, sum = 0; //设置为长度+1
        while (r < nums.length) {
            sum += nums[r];
            while (sum >= target) {       //l右滑动,减少窗口里面的元素,直到窗口小于target
                res = Math.min(res, r - l + 1);     //更新res
                sum -= nums[l++];         //l右移动
            }
            r++;
        }
        return res > nums.length ? 0 : res;      //如果res改变了就肯定存在,直接返回
    }
}
