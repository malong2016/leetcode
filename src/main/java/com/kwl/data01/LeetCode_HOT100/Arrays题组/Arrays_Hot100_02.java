package com.kwl.data01.LeetCode_HOT100.Arrays题组;

/**
 * leetcode热题HOT100_数组_题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_02 {


    /**
     * 题目1(leetcode 896题): 如果数组是单调递增或单调递减的，那么它是单调的。
     * 描述: 前后可以等于!!! 可以顺序或者到顺
     * eg: [1,2,2,3]  --> true   [1,3,2] -- false
     * <p>
     * 思路01(一次遍历):先设置inc = true,des = true, 如果遇到arr[i]>arr[i+1] 就把inc设置为false,反之把des设置为false,
     * 最后inc||des,有一个为true就是true
     * 思路02(二次遍历): 同时验证是升序或者是降序,满足其一就可以return isSorted(A, true) || isSorted(A, false);
     */
    public boolean isMonotonic(int[] A) {
        if (A == null) return true;
        boolean inc = true, des = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) inc = false;
            if (A[i] < A[i + 1]) des = false;
        }
        return inc || des;
    }
    /**
     * 题目2(leetcode 35题): 搜索插入位置  todo 折半插入leetcode35题
     * 描述: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 注意: 你可以假设数组中无重复元素。
     */

    /**
     * 题目3(leetcode 55题): 跳跃游戏  todo 折半插入leetcode35题
     * 描述: 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     * <p>
     * eg01:
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * eg02:
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     * <p>
     * 思路01(官方解): 想要跳到最后一个那么在该点的nums[i] + i >= nums.length - 1 (这里是nums.length - 1个空隙)
     * 不断维护maxIndex,当扫描到当前i,先进行比较，通过后再继续判断和维护
     */
    public boolean canJump(int[] nums) {
        //顺序扫描nums,维护最大跳跃maxIndex,如果扫描到i,先判断如果小于maxIndex,直接
        //返回break,如果大于等于，继续判断维护
        //核心是nums[i] + i >= nums.length -1就成功了
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > maxIndex) break; //不能跳到i，那么就一定不能跳到nums.length-1
            else {
                if (nums[i] + i >= nums.length -1) return true;    //当前i能跳跃到末尾，就返回true,不能跳到继续维护
                maxIndex = Math.max(maxIndex, nums[i] + i);
            }
        }
        return false;
    }


}
