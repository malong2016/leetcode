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
     * 题目01(swordOffer 第14题-I): 剪绳子
     * 描述: 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
     * <p>
     * <p>
     * 思路01(动态规划法):从下到上进行计算,先计算到f(1)max(绳子长度为1的最大值),f(2)max...f(length)max
     * 状态转移方程式: f(length)max = f(i)max*f(length-i)max
     */
    public int maxProductAfterCutting01(int n) {        //思路01,使用动态规划求解!!!
        if (n <= 3) return n - 1;  //3 = 2 * 1,2 = 1*1;
        int[] dp = new int[n + 1];
        dp[1] = 1;          //因为如果大于4，不分割1,2，3是更大的，这三种情况要分开讨论
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < dp.length; i++) {
            // for (int j = 1; j < i; j++) {
            for (int j = 1; j <= i / 2; j++) { //优化，比较前半段就可以了
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
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
     * 题目04(swordOffer 第42题): 连续子数组的最大和     --本题和leetcode的53题重合
     * 描述: 输入一个整型数组，数组中存在正数和负数。数组中的一个或者连续多个整数
     * 组成一个子数组。求所有子数组的和的最大值。要求时间复杂度是o(n)
     * eg: 输入{1,-2,3,10,-4,7,2,-5} 最大子数组是{3,10,-4,7,2}
     * 输出该子数组的和是18
     * <p>
     * 思路01(leetcode): 利用动态规划,先求出上一个i-1的最大子数列,和0进行比较,小于等于0就取0
     * 大于0就取本值
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0], res = nums[0];
        for(int i = 1; i < nums.length; i++){
            pre = Math.max(nums[i], nums[i] + pre);   //可以选择pre，或者就是本身
            res = Math.max(res, pre);
        }
        return res;
    }
}
