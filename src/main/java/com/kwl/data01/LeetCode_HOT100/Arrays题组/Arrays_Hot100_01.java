package com.kwl.data01.LeetCode_HOT100.Arrays题组;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode热题HOT100_数组_题组01(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_01 {

    /**
     * 题目1(leetcode 1题):二数之和
     * 描述: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值的那两个整数,并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。你可以按任意顺序返回答案
     * eg: 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
     * <p>
     * 思路01: 暴力解
     * 思路02: 将nums[i]和i写入到HashMap之中
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] index = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                index[0] = map.get(target - nums[i]);
                index[1] = i;
                return index;
            }
            map.put(nums[i], i);
        }
        return index;
    }

    /**
     * 题目2(leetcode 4题):寻找二个正序数组的中位数,如果中位数是二个,就取平均
     * eg: nums1 = [1,2], nums2 = [3,4]  --> (2+3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 1;
    }

    /**
     * 题目3(leetcode 53题):最大子序列
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
     * 题目4(leetcode 33题): 搜索旋转数组
     * 描述: 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * <p>
     * eg: nums = [4,5,6,7,0,1,2], target = 0  --> 4
     * <p>
     * 思路:二分查找,先二分数组,肯定存在一个有序,一个无序(通过首尾指针判断),不断缩小范围,使得target在有序表中!!
     */
    public int search(int[] nums, int target) {
        return 0;
    }

    /**
     * 题目5(leetcode 34题): 在排序数组中查找元素的第一个和最后一个位置  (类似swordOffer 面试题53)
     * 描述: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶: 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * eg: nums = [5,7,7,8,8,10], target = 8 --> [3,4]
     * <p>
     * 思路01: 顺序查找,时间复杂度是o(n)
     * 思路02: 二分查找,时间复杂度是o(log2n)  todo
     */
    public static int[] searchRange(int[] nums, int target) {

        return null;
    }

    /**
     * 题目6(leetcode 560题): 和为k的子数组
     * 描述: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * eg: 输入: nums = [1,1,1], k = 2  --> 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     */
    public int subarraySum(int[] nums, int k) {
        return 0;
    }


    /**
     * 题目7(leetcode 283题): 移动零
     * 描述: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * eg:输入: [0,1,0,3,12]  --> 输出: [1,3,12,0,0]
     * 说明:  1必须在原数组上操作，不能拷贝额外的数组。
     * 2尽量减少操作次数。
     * <p>
     * 思路01(my): 暴力解,扫描到0,后面元素直接向前移动
     */
    public static void moveZeroes(int[] nums) {
    }

    /**
     * 题目8(leetcode 647题): 回文子串
     * 描述:给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * eg:输入："aaa" --> 输出：6  (6个回文子串: "a", "a", "a", "aa", "aa", "aaa")
     * <p>
     * 思路01(动态规划):
     */
    public int countSubstrings(String s) {
        //使用中心扩展,不断获取到中心,设置左右指针,向二端进行移动
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int left = i / 2, right = i / 2 + i % 2;            //设置左右指针
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }
        }
        return res;
    }


}
