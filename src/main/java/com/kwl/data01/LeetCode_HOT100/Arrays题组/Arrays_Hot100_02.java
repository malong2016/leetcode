package com.kwl.data01.LeetCode_HOT100.Arrays题组;

import com.kwl.data01.algorithm.ArraysSort;

import java.util.*;

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
     * 题目3(leetcode 第215题): 数组中的第K个最大元素
     * 描述: 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 思路01: 先排序Arrays.sort(nums);返回  return nums[nums.length-k];
     * 思路02: 快速排序和堆排序
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    int quickSort(int[] nums, int low, int high, int index) {     //要找元素的index
//        if (low < high) {           //这种标准循环就不行,必须使用逼近型，因为可能排好，就不用该index
//            int mid = findMid(nums, low, high);
//            if (mid == index) {
//                res = nums[mid];
//                System.out.println("找到了res = " + res);
//            }
//            quickSort(nums, low, mid - 1,index);
//            quickSort(nums, mid + 1, high,index);
//        }
        int mid = ArraysSort.partition(nums, low, high);    //这是我们自带的
        if (mid == index) {
            return nums[mid];
        } else {
            //mid大于就去小的地方搜索
            return mid > index ? quickSort(nums, low, mid - 1, index) : quickSort(nums, mid + 1, high, index);
        }
    }

    /**
     * 题目3(leetcode 第136题): 只出现一次的数字
     * 描述: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 思路01: res = 0,在遍历数组进行异或,相同会异或为0,最后剩下就是所求的值
     * 思路02: HashSet,如果不存在就添加，存在就移除，最后剩下的数的就是res
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /**
     * 题目4(leetcode 第238题): 除自身以外数组的乘积 -- 本题和swordOffer 66题一样
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i--) {
            res[i] = res[i - 1] * nums[i - 1];   //把0,1...i-1累乘
        }
        int temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {    //注意是j--
            res[i] *= temp;
            temp = temp * nums[i];    //把i+1...num.length-1累乘
        }
        return res;
    }

    /**
     * 题目4(leetcode 739题): 每日温度
     * 描述: 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
     * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替
     * <p>
     * eg:  输入: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 输出:  [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 思路01(暴力解): 双指针扫描
     * 思路02(单调栈，单调递减栈): 按照顺序扫描数组，注意是index入栈，入栈时候，比较入栈index对应的value,和栈顶大小，把比入栈value小的节点都
     * 出栈，记录结果，利用单调递减的特效保持单调栈
     */
    public int[] dailyTemperatures(int[] temperatures) {     //暴力解
        int n = temperatures.length;
        int[] res = new int[n];
        for (int i = 0; i < n - 1; i++) {   //最后一天不需要计算
            for (int j = i; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {     //找到了第一个大于i的直接加入res,结束本次循环
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures01(int[] temperatures) {       //单调栈
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer popIndex = stack.pop();   //要出栈的index
                res[popIndex] = i - popIndex;    //是哪个节点要你出栈的，之差就是结果
            }
            stack.push(i);  //无论如何都是要把i入队的
        }
        return res;
    }

    /**
     * 题目4(leetcode 416题): 分割等和子集
     * 描述: 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * <p>
     * eg01:
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11]
     * eg02:
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     * <p>
     * 思路01(my): 直接动态规划,0 -1 背包问题，是否能找出一些数，使得这些数的和是sum/2  todo 先学生0/1背包问题
     */
    public boolean canPartition(int[] nums) {
        return false;
    }

    /**
     * 题目5(leetcode 第240题): 搜索二维矩阵 II              ---本题和 swordOffer 第4题 重复
     * 描述: 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * eg:
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     * <p>
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
     * 输出：false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) i--;   //更小值里面找
            else j++;   //更大的值找
        }
        return false;
    }

    /**
     * 题目6(leetcode 240题): 岛屿数量
     * 描述: 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 注意: 此外，你可以假设该网格的四条边均被水包围。。
     * <p>
     * eg:
     * 输入：grid = [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]
     * ]
     * 输出：1
     * <p>
     * 思路01: 暴力解
     */
    public int numIslands(char[][] grid) {
        return 0;
    }

    public static void main(String[] args) {

    }
    /**
     * 题目6(leetcode 239题): 岛屿数量
     */


}
