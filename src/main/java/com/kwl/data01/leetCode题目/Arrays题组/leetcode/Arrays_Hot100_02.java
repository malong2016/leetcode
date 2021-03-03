package com.kwl.data01.leetCode题目.Arrays题组.leetcode;

/**
 * leetcode热题HOT100_数组_题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_02 {


    /**
     * 题目1(leetcode 33题): 搜索旋转数组
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
     * 题目2(leetcode 34题): 在排序数组中查找元素的第一个和最后一个位置  (类似swordOffer 面试题53)
     * 描述: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶: 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * eg: nums = [5,7,7,8,8,10], target = 8 --> [3,4]
     * <p>
     * 思路01: 顺序查找,时间复杂度是o(n)
     * 思路02: 二分查找,时间复杂度是o(log2n)
     * 查到startindex,如果startindex-1的value不等于target,就是,如果等于,继续向下查找
     * endindex也差不多
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null) return null;
        return new int[]{getStartIndex(nums,target),getEndIndex(nums,target)};
    }

    public static int getStartIndex(int[] nums, int target) {      //第一个值没有找到[5,7,7,8,8,10]
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                if (mid == begin || nums[mid - 1] != target) return mid; //说明这个是第一个索引
                else end = mid - 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return  -1;       //没有找到,返回-1
    }

    public static int getEndIndex(int[] nums,int target) {
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                if (mid == end || nums[mid + 1] != target) return mid; //说明这个是最后一个索引
                else begin = mid + 1;
            } else if (nums[mid] > target) {    //下半区查找
                end = mid - 1;
            } else {                  //上半区查找
                begin = mid + 1;
            }
        }
        return  -1;       //没有找到,返回-1
    }
    /**
     * 题目3(leetcode 560题): 和为k的子数组
     * 描述: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * eg: 输入: nums = [1,1,1], k = 2  --> 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     */
    public int subarraySum(int[] nums, int k) {
        return 0;
    }

    /**
     * 题目4(leetcode 79题): 单词搜索
     * 描述: 给定一个二维网格和一个单词，找出该单词是否存在于网格中
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * eg: board = [
     *    ['A','B','C','E'],
     *    ['S','F','C','S'],
     *    ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true  给定 word = "SEE", 返回 true
     */
    public boolean exist(char[][] board, String word) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("题目2(leetcode 34题): 在排序数组中查找元素的第一个和最后一个位置  (类似swordOffer 面试题53):");
        System.out.println(getStartIndex(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }
}
