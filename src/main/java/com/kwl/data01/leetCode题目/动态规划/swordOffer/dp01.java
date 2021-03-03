package com.kwl.data01.leetCode题目.动态规划.swordOffer;

/**
 * @author kuang.weilin
 * @date 2021/2/26
 */
public class dp01 {

    /**
     * 题目1(leetcode 62题):不同路径
     * 描述: 一个机器人位于m*n的左上角,他只能向下或者向右移动,机器人从左上角到右下角,多多少种路径
     * <p>
     * 思路01(my):动态规划,坐标(m,n) = (m-1,n)+(n-1,m)
     */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return -1;
        int dp[][] = new int[m][n];      //动态规划每一个格子的有多少种情况

        for (int i = 0; i < m; i++) {        //每一行
            for (int j = 0; j < n; j++) {     //每一列
                if (i == 0) dp[i][j] = 1;
                else if (j == 0) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 题目2(leetcode 70题): 爬楼梯
     * 描述: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * eg: 输入3 有三种方法可以爬到楼顶
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * 思路01(my): 动态规划
     * 初始状态: f(1) = 1,f(2) = 2
     * 状态转移方程:  f(n-1)+f(n-2) 爬1格或者爬二格子
     */
    public int climbStairs(int n) {
        if (n == 0) return -2;
        if (n == 1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 题目3(leetcode 279):完全平方数
     * 描述: 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数: 是一个整数，其值等于另一个整数的平方
     * <p>
     * 思路01: 动态规划 {
     * 初始状态: f(0)
     * 状态转移方程: f(n) = min(f(i),dp(i-J*J)+1)        //初始化f(n) = n
     * 终止状态: f(n)
     * }
     */
    public int numSquares(int n) {
        if (n < 0) return 0;
        int[] dp = new int[n + 1];          //初始化为1,因为要使用到f(0)所有要n+1
        for (int i = 1; i < n + 1; i++) {      //从1开始计算
            dp[i] = i;        //全部是1+1+1+1....
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 题目4(leetcode 198): 打家劫舍
     * 描述: 数组代表一排房屋,数组值代表房屋东西的价值的价值,如果小偷偷相邻的房子的东西,就会触发报警
     * 求小偷在不触发报警的情况下,最大能偷多少价值的东西(如果不偷return 0)
     * eg: [1,2,3,1] --> 偷1和3,输出价值为4
     * <p>
     * 思路01:动态规划{
     * 初始状态: f(0) = arr[0],f(1) = max(arr[0],arr[1])
     * 状态转移方程: f(n) = max(f(n-1),f(n-2)+arr[i])   (注意:f(n-1)代表不偷当前房屋,f(n-2)+arr[i]代表偷当前房屋)
     * 终止状态: f(n)
     * }
     * 1)使用dp[nums.length]记录每一种情况的值,空间复杂度是o(n)
     * 2)使用cur维护i-1,prev维护i-2,使得空间复杂度o(1),滚动数组
     */
    public int rob(int[] nums) {        //方法一:我们使用额外的数组,存储每一个房屋Max
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int dp[] = new int[nums.length];          //注意:num.length>1;
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int rob01(int[] nums) {        //方法二:使用cur维护i-1,prev维护i-2,使得空间复杂度o(1)
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev = nums[0],  cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = cur;
            cur = Math.max(cur, prev + nums[i]);
            prev = temp;
        }
        return cur;
    }


    public static void main(String[] args) {
    }
}
