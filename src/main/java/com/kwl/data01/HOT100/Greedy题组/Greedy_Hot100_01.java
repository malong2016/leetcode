package com.kwl.data01.HOT100.Greedy题组;

/**
 * @author kuang.weilin
 * @date 2021/7/4 0:48
 */
public class Greedy_Hot100_01 {
    /**
     * 题目01(leetcode 第55题): 跳跃游戏
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
        int maxIndex = 0;     //记录当前，能跳的最大index
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
