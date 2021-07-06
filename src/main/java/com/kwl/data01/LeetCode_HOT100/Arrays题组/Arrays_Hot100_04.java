package com.kwl.data01.LeetCode_HOT100.Arrays题组;

import java.util.LinkedList;

/**
 * @author kuang.weilin
 * @date 2021/7/7 0:17
 */
public class Arrays_Hot100_04 {

    /**
     * 题目01(leetcode 第239题): 滑动窗口最大值                  --本题和swordOffer 面试题59 重复
     * 描述: 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值
     * 思路: 单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 1-k,j = 0; j < nums.length; i++,j++) {
            if (i > 0 && nums[i - 1] == linkedList.peekFirst()) linkedList.pollFirst();  //可能前面的值已经被清除掉了
            while(!linkedList.isEmpty() && linkedList.peekLast() < nums[j]) linkedList.pollLast();
            linkedList.addLast(nums[j]);
            if (i >= 0) res[i] = linkedList.peekFirst();
        }
        return res;
    }
    /**
     * 题目8(leetcode 15题):三数之和
     * 描述: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * eg: nums = [-1,0,1,2,-1,-4] --> [[-1,-1,2],[-1,0,1]]
     * <p>
     * 思路01: 固定一端，左右扫描
     */
}
