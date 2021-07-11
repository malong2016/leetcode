package com.kwl.data01.HOT100.动态规划;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class dpSword_Hot100_01 {

    /**
     * 题目01(leetcode 第62题): 不同路径
     * 描述: 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * 思路01: 动态规划
     * 初始状态: i == 0,j ==0时候,dp[i][j] = 1
     * 状态转移方程式: dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths01(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {        //先初始化，避免后面判断
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 题目02(leetcode 第64题): 最小路径和
     * 描述:给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 每次只能[向下或者向右]移动一步。
     * 思路01: 动态规划
     * 初始状态: i == 0,dp[i][j] = dp[0][j-1] + grid[0][j]      //这是第一行
     * j ==0时候,dp[i][j] = dp[i-1][0] + grid[i][0]     //这是第一列
     * 状态转移方程式: dp[i][j] = Math.max(dp[i - 1][j] + dp[i][j - 1]) + grid[i][j]
     */
    public int minPathSum(int[][] grid) { //我们直接开辟路径dp[m][n]
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {     //巧记: m * n,如果遍历的是m,那么是横坐标
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum01(int[][] grid) {       //原地填充,更快
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < n; i++) grid[0][i] += grid[0][i - 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 题目03(leetcode 第221题): 最大正方形
     * 描述: 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：4
     * 思路01：动态规划
     * 初始值: i == 0 || j == 0 && char[i][j] = '1',dp[i][j] = 1
     * 状态转移方程式: dp[i][j] = min(dp[i-1][j-1] + dp[i][j-1] + dp[i-1][j]) + 1;
     * 终止状态: 使用res，不断记录最大的边长
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    /**
     * 题目05(leetcode 第84题): 柱状图中最大的矩形
     * 描述: 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <p>
     * eg:
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     * <p>
     *  本题核心是找左和右第一个比他小的值，由于是单调栈，所以出栈之后栈顶就是right比他小的值，逼他出栈就是左边逼他小的值！！
     * 思路01(暴力解,宽扫描): 固定一端，双指针扫描width,然后求出最小的高，计算res
     * 思路02(暴力解，高扫描): 确定一个柱形，然后左右分别扫描，扫描到比这个高度小的，就是边界
     * 思路03(单调递增栈): 是高扫描的优化。
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length, res = 0;
        for (int i = 0; i < len; i++) {
            int high = heights[i];
            if (high * len <= res) continue;        //如果左右全部扫描得到的面积都是小于res,就不用计算了
            int l = i, r = i; //确定左右指针向二段扫描
            while (l - 1 >= 0 && heights[l - 1] >= high) l--; //这个l是符合最大矩阵的情况
            while (r + 1 <= len - 1 && heights[r + 1] >= high) r++;
            res = Math.max(res, (r - l + 1) * high);


            //直接循环的时候计算宽度，更好理解
//            int width = 1;
//            while (--l >= 0 && heights[l] >= high) width++;
//            while (++r <= len - 1 && heights[r] >= high) width++;
//            res = Math.max(res, width * high);
        }
        return res;
    }
    public int largestRectangleArea01(int[] heights) {       //单调递增栈
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);   //存储一个哨兵
        for (int i = 0; i <= heights.length; i++) {
            int cur = i < heights.length ? heights[i] : 0;    //最后用0去逼出最后的元素
            while (stack.peek()!= -1 && !stack.isEmpty() && heights[stack.peek()] > cur) {    //如果比入栈元素大就出栈
                Integer temp = stack.pop(); //记录出栈的索引
                res = Math.max(res, (i - stack.peek() - 1) * heights[temp]);  //计算此时的最大面积
            }
            stack.push(i);     //无论这么样,都是要入栈的
        }
        return res;
    }


        /**
         * 题目06(leetcode 第85题): 最大矩阵
         * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
         * eg:
         * 输入：matrix = [
         * ["1","0","1","0","0"],
         * ["1","0","1","1","1"],
         * ["1","1","1","1","1"],
         * ["1","0","0","1","0"]
         * ]
         * 输出：6
         * 解释：最大矩形如上图所示。
         *
         * 思路01: 高扫描，和leetcode84题柱形最大面积差不多
         * 每层看做是个柱形图，如果是1是height[i]++,如果是height是直接设置为0；
         * 我们遍历每行，计算出height[i]，传入到柱形的最大面积，就可以计算当前的最大面积
         */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, res = 0;    //res要设置为0，防止遍历不了的情况
        int[] height = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
          res = Math.max(res,largestRectangleArea(height));
        }
        return res;
    }

    /**
     * 题目07(leetcode 第152题): 乘积最大子数组
     * 描述: 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * <p>
     * eg: [-2,3,-4]  --> 24
     * <p>
     * 思路01:如果当前是nums[i] > 0,就是取前面的最大值,如果是nums[i] < 0，就是取前面的最小值!!所以我们要同时记录最大值和最小值
     */
    public int maxProduct(int[] nums) {        //直接迭代
        int res = Integer.MIN_VALUE, max = 1, min = 1;
        for (int num : nums) {
            int temp = max;         //max在改变
            max = Math.max(num, Math.max(max * num, min * num));
            min = Math.min(num, Math.min(temp * num, min * num));
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 题目08(leetcode 第121题) 买卖股票的最佳时机
     * 描述: 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 输入: [7,1,5,3,6,4] --> 5
     * <p>
     * 思路: 见下面
     */
    public int maxProfit(int[] prices) {
        int res = 0, pre = Integer.MAX_VALUE;      //pre要设置为最大值,因为在第一天要买入
        for (int price : prices) {
            if (price > pre) res = Math.max(res, price - pre);
            else pre = price;
        }
        return res;
    }


}
