package com.kwl.data01.swordOffer.动态规划;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/7/2 21:31
 */
public class dpSword_02 {

    /**
     * 题目01(swordOffer 第10题-II): 青蛙跳台阶问题
     * 描述: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;      //这里注意青蛙跳，n==0要特别处理!!
        if (n == 2) return 2;
        int p1 = 1, p2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = (p1 + p2) % 1000000007;
            p1 = p2;
            p2 = temp;
        }
        return p2;
    }

    /**
     * 题目02(swordOffer 第12题): 矩阵中的路径
     * 描述: 给定一个n*m的矩阵,可以从任意一格开始沿着上下左右移动一格,但是如果已经经过了某一格就不能再次
     * 进入这个格子。
     * 设计一个函数,给定一个路径,判断这个矩阵是否包括这个路径,如果包括返回true,否则返回false
     * <p>
     * 下面是矩阵举例图:
     * {a ,b ,t ,g}
     * {c ,f ,c ,s}
     * {j ,d ,e ,h}
     * <p>
     * 思路01(leetcode): 深度优先搜索(DFS)+剪枝(在搜索中,遇到这条路不可能和目标字符串匹配的情况,立刻返回,被叫做可行性的剪枝)
     */
    public boolean hasPath(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k])
            return false;    //越界是直接返回false
        if (k == word.length - 1) return true;
        board[i][j] = '\0';    //空字符(NULL)
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k]; //board[i][j]一定是等于word[k]的
        return res;
    }

    /**
     * 题目03(swordOffer 第13题): 机器人的运动范围
     * 描述: 地上有一个m*n的方格。一个机器人在坐标为(0,0)的格子开始运动,每次可以
     * 左,右,上,下的运动一格,但是不能进入行坐标和列坐标的数位大于k的格子。
     * eg: 当k为18的时候,机器人能进入(35,38),因为是3+5+3+7=18,但是不能进入(35,38)
     * 因为是3+5+3+8 = 19
     * 设计一个函数,求机器人能到达多少个格子.
     * <p>
     * 思路01(leetcode): 使用回溯法(dfs)解决问题,注意这里可以来回走
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visit = new boolean[m][n];     //访问过就设置为true,没有访问就是false
        return dfs(0, 0, m, n, k, visit);
    }

    int dfs(int i, int j, int m, int n, int k, boolean[][] visit) {
        //越界,不满足条件或者已经访问过，就返回0
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || (i % 10 + i / 10 + j % 10 + j / 10) > k || visit[i][j]){
            return 0;
        }
        visit[i][j] = true;     //该点已经访问过，设置为true
        return dfs(i - 1, j, m, n, k, visit) + dfs(i + 1, j, m, n, k, visit)
                + dfs(i, j - 1, m, n, k, visit) + dfs(i, j + 1, m, n, k, visit) + 1;   //上下左右继续走，累加
    }

    /**
     * 题目04(swordOffer 第42题): 连续子数组的最大和
     * 描述: 输入一个整型数组，数组中存在正数和负数。数组中的一个或者连续多个整数
     * 组成一个子数组。求所有子数组的和的最大值。要求时间复杂度是o(n)
     * eg: 输入{1,-2,3,10,-4,7,2,-5} 最大子数组是{3,10,-4,7,2}
     * 输出该子数组的和是18
     * <p>
     * 思路01(leetcode): 利用动态规划,先求出上一个i-1的最大子数列,和0进行比较,小于等于0就取0
     * 大于0就取本值
     */
    public int FindGreatestSumOfSumOfSubArray(int[] arr) {
        int res = arr[0];    //默认是res是arr[0]
        for (int i = 1; i < arr.length; i++) {
            arr[i] += Math.max(arr[i - 1], 0);
            res = Math.max(arr[i], res);
        }
        return res;
    }

}
