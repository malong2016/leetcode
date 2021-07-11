package com.kwl.data01.HOT100.动态规划;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kuang.weilin
 * @date 2021/7/6 12:38
 */
public class dpSword_Hot100_02 {

    /**
     * 题目01(leetcode 第53题):最大子序和
     * 描述: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * eg: nums = [-2,1,-3,4,-1,2,1,-5,4]  -->连续子数组 [4,-1,2,1] 的和最大，为 6
     * <p>
     * 思路01: 设置一个pre维护,当前数的前序,然后pre+arr[i]和arr[i]比较,取最大值
     * 用max记录最大值
     */
    public int maxSubArray(int[] nums) {   //直接累计
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = pre > 0 ? pre + num : num;
            max = Math.max(max, pre);
        }
        return max;
    }

    public int maxSubArray01(int[] nums) {    //dp法
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 题目02(leetcode 第70题): 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
     * <p>
     * 使用动态规划求解,本题不需要考虑0
     * 思路01: 设置前置pre
     * 思路02: dp[n]
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) return n;   //如果包括0一般就是0 ~ n,不包括从0开始的化就是0 ~ n -1
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public int climbStairs01(int n) {        //dp方法
        if (n == 1) return 1;
        int[] dp = new int[n];      // n阶楼梯存储到n-1索引的数组下
        dp[0] = 1;
        dp[1] = 2;           //如果是2阶楼梯，有二种方法
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 题目03(leetcode 第139题): 单词拆分
     * 描述: 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * eg:
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * <p>
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * <p>
     * 思路01: 先固定一端j,在从i从0扫描到j-1,如果前半段是true(可以差分)+ sub(i+1,j)满足条件 (就是前后半段都可以拆分)
     * ,那么就可以设置为true.
     * 注意dp[0] = 0,0是可以拆分的 要设置为dp[len+1]
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);      //存入hashSet进行判断
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int j = 1; j <= s.length(); j++) {   //因为是截切j是不包括
            for (int i = 0; i < j; i++) {
                if (dp[i] && set.contains(s.substring(i, j))) {
                    dp[j] = true;
                    break;              //如果当前可以拆分,直接跳出本次循环
                }
            }
        }
        return dp[s.length()];
    }



    /**
     * 题目04(leetcode 第200题): 岛屿数量
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
     * 思路01: 如果遇到为1，那么就向周围扩散，让值为1变成值为2,在递归上下左右进行扩散
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] != '1') return; //越界或者不等于1就直接返回
        grid[i][j] = '2';   //如果符合条件,就设置为'2',也代表已经访问
        dfs(grid, i - 1, j);              //上下左右进行扩散
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * 题目05(leetcode 第198题):  打家劫舍
     * 描述:你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * <p>
     * 思路01: 核心就是偷前面和不偷前面的,dp[i]维护当前点的最大值
     * dp[i] = dp[i - 1] + dp[i - 2] + nums[i]  (偷前面和不偷前面的)
     */
    public int rob(int[] nums) {          //dp方法
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int rob02(int[] nums) {          //pre方法
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int pre = nums[0], cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = cur;
            cur = Math.max(cur, pre + nums[i]);     //更新cur,注意这里有i所以要从2~len-1,否则一般是3~len
            pre = temp;             //更新pre
        }
        return cur;
    }

    /**
     * 题目06(leetcode 第337题):  打家劫舍 III
     * 描述:房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * <p>
     * eg:
     * 输入: [3,2,3,null,3,null,1]
     * <p>
     * 3
     * / \
     * 2   3
     * \   \
     * 3   1
     * <p>
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 思路01: 后序遍历 + 动态规划
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    int[] dfs(TreeNode root) {        //int[0]代表不偷,int[1]代表偷
        if (root == null) return new int[]{0, 0};      //就null，偷和不偷都是0
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] dp = new int[2];
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);     //不偷root节点,可以选择偷子节点和不偷子节点
        dp[1] = root.val + left[0] + right[0];   //偷了root节点,就不能偷左右字节
        return dp;
    }

    /**
     * 题目07(leetcode 第322题): 零钱兑换
     * 描述: 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * eg:
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * <p>
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * <p>
     * 输入：coins = [1], amount = 0
     * 输出：0
     * <p>
     * 思路01: 动态规划,dp[i] = n,代表i元可以换最小的硬币,dp[0] = 0
     * 思路02:
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //最多就是全部是amount!!设置+1
        dp[0] = 0;       //0元换0个硬币
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {       //硬币小于i就可以换
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);  //如果换不成，这里还是会返回amount+1
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 题目08(leetcode 第309题): 最佳买卖股票时机含冷冻期
     * 描述: 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。。
     * <p>
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 思路: f[i][0]: 手上持有股票的最大收益  (昨天就有股票or今天买入)
     * f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益    (今天卖出)
     * f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益  (今天一开始就没有股票)
     *
     * 初始化:   dp[0][0] = -prices[0];
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 1) return 0;
        //1是持有股票(昨天就有股票or今天买入)2是不持有股票，在冷冻期(今天卖出),3是不持有股票，也不在冷冻期(今天一开始就没有股票)
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];    //第一天买入直接是-收益，dp[0][0]初始化为0
        for (int i = 1; i < len; i++) {
            //如果在当天持有股票.说明是 1 昨天持有股票 2 昨天不持有，而且不处于冷冻期。今天买入！！！
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //不持有股票，而且在冷冻期，说明是 1 今天卖出，那么肯定是昨天持有
            dp[i][1] = dp[i - 1][0] + prices[i];
            //现在不持有股票,不在冷冻期(今天开始就没有股票)。说明是 1 昨天不持有，在冷冻期 2 昨天不持有，不在冷冻期
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);     //注意dp[len-1][0]持有股票是没有意义的
    }

}
