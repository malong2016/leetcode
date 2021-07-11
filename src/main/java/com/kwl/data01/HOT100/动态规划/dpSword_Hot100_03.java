package com.kwl.data01.HOT100.动态规划;

import java.util.Arrays;
import java.util.Map;

/**
 * @author kuang.weilin
 * @date 2021/7/7 22:21
 */
public class dpSword_Hot100_03 {

    /**
     * 题目01(leetcode 第279题): 完全平方数
     * 描述:给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     * <p>
     * eg：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * <p>
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     * <p>
     * 思路01:  初始化是dp[0] = 0,dp[1]代表就是输入1
     * 动态规划，dp[i - j*j] + 1(注意,此时是j*j构成完全平方数,所以就是要dp+1)
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];    //我们初始化包括dp[0] = 0,dp[16 - 4*4]+1=dp[0]+1,正好是1(不要考虑0*0!!!!)
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;                   //dp[i]初始化,最大值是1*1 + 1*1 ....
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.max(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 题目02(leetcode 第312题): 戳气球
     * 描述: 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     *  这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * 求所能获得硬币的最大数量。
     * <p>
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     * <p>
     * 思路: 动态规划 dp[i,j] 开区间，代表i+1,j-1见搓破气球的最大值(因为要靠考虑周围的值)
     */
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len + 2];    //temp左右二端要加入1
        int[][] dp = new int[len + 2][len + 2];    //dp记录开区间
        for (int i = 0; i < nums.length; i++) {
            temp[i + 1] = nums[i];
        }
        temp[0] = temp[len + 1] = 1;              //首尾设置为1
        for (int i = len - 1; i >= 0; i--) {      //控制最左端,起始值是到3
            for (int j = i + 2; j <= len + 1; j++) {    //控制最右端
                for (int k = i + 1; k < j; k++) {     //在中间走
                    int sum = temp[i] * temp[k] * temp[j] + dp[i][k] + dp[k][j];    //这个是本次的值,初始值把k戳破,还是会累乘
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][len + 1];
    }

    /**
     * 题目03(leetcode 第10题): 正则表达式匹配    --本题和swordOffer 第19题 一样
     * 描述: 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];  //注意这里是包括0的的
        dp[0][0] = true;
        for (int i = 2; i < n + 1; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';  //可以消除掉前面一个值
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    //j多余出来二个值,那么就让*把前面哪个值消失掉
                    if (dp[i][j - 2]) dp[i][j] = true;
                        //即让字符 p[j - 2] 多出现 1 次时，能否匹配；
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
                        // 即让字符 '.' 多出现 1 次时，能否匹配；
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 题目04(leetcode 第300题): 最长递增子序列
     * 描述: 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 思路(动态规划): 双循环,先固定右端j,然后从i从0开始遍历到j-1,如果dp[j] > dp[i]就在对应的dp[i]+1
     * dp[]初始值都是要设置为1，双循环不断的更新dp
     */
    public int lengthOfLIS(int[] nums) {    //从o开始
        int[] dp = new int[nums.length];
        int res = Integer.MIN_VALUE;
        Arrays.fill(dp, 1);       //初始值全部赋值为1
        for (int j = 0; j < nums.length; j++) {                //j是固定的右端,如果每次都是统计dp[j]的值,只要是前面的值都可以
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) dp[j] = Math.max(dp[i] + 1, dp[j]);        //这里是dp[j]符合升序才要换!!
            }
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    public int lengthOfLIS01(int[] nums) {       //从1开始
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) dp[j] = Math.max(dp[i] + 1, dp[j]);
            }
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    /**
     * 题目05(leetcode 第416题): 分割等和子集
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
     * 注意: 当sum是奇数的时候,是不能分割的.target=sum/2 < max不能
     * 思路01: 二维dp[i][j] i代表nums的index,j代表sum/2的数量,
     * 思路02: 一维数组dp[i],代表本sum/2的数量
     */
    public boolean canPartition(int[] nums) {          //二维
        int sum = 0, max = Integer.MIN_VALUE, n = nums.length;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        int target = sum / 2;
        if (sum % 2 == 1 || target < max) return false;        //如果sum是奇数或者target小于max直接返回false
        boolean[][] dp = new boolean[n][target + 1];     //当target==0,是直接返回true的
        for (int i = 0; i < n; i++) {     //初始化i=0/j=0的情况
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;      //就一个数,nums[0]为分割,其他是false
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n - 1][target];
    }

    public boolean canPartition01(int[] nums) {          //一维
        int sum = 0, max = Integer.MIN_VALUE, n = nums.length;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        int target = sum / 2;
        if (sum % 2 == 1 || target < max) return false;        //如果sum是奇数或者target小于max直接返回false
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];       //等于以前求得,和现在的关系
            }
        }
        return dp[dp.length - 1];
    }

}
