package com.kwl.data01.LeetCode_HOT100.动态规划;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class dpSword_Hot100_01 {

    /**
     * 题目1(leetcode 62题): 不同路径
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
     * 题目1(leetcode 64题): 最小路径和
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
        for (int i = 1; i < m; i++) {
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

}
