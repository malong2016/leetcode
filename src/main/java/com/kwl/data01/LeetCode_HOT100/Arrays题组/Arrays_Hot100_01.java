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
     * 题目3(leetcode 15题):三数之和
     * 描述: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * eg: nums = [-1,0,1,2,-1,-4] --> [[-1,-1,2],[-1,0,1]]
     * <p>
     * 思路01: 暴力解
     * 思路02: 先排序 在三指针移动
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    /**
     * 题目4(leetcode 53题):最大子序列
     * 描述: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * eg: nums = [-2,1,-3,4,-1,2,1,-5,4]  -->连续子数组 [4,-1,2,1] 的和最大，为 6
     * <p>
     * 思路01: 设置一个pre维护,当前数的前序,然后pre+arr[i]和arr[i]比较,取最大值
     * 用max记录最大值
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int pre = 0, max = nums[0];
        for (int num : nums) {
//            pre = pre + num > num ? pre + num : num;
            pre = pre > 0 ? pre + num : num;
            max = Math.max(max, pre);
        }
        return max;
    }

}
