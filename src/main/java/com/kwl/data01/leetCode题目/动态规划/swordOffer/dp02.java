package com.kwl.data01.leetCode题目.动态规划.swordOffer;

/**
 * @author kuang.weilin
 * @date 2021/2/27
 */
public class dp02 {


    /**
     * 题目1(leetcode 5题): 最长回文子串
     * 描述: 给定一个字符串s,找到s中最长的回文子串
     * eg: s = "babad" --> "bab"
     * <p>
     * 思路01(动态规划): dp[][] = new dp[i][j], dp[i][j] = dp[i+1][j-1]&&arr[i]==arr[j] 进行判断,如果拿到true,就和
     * 最长子串进行比较,如果大于就替换为当前的最长的子串.
     * 思路02(中心集扩散)
     */
    public String longestPalindrome(String s) {    //方法一: 动态规划dp[i][j] = dp[i+1][j-1]&&arr[i]==arr[j]
        if (s == null || s.length() == 1) return s;
        Boolean dp[][] = new Boolean[s.length()][s.length()];
//        for (int i = 0; i < s.length(); i++) {        //如果只有一个字符,那么是回文字符,可以省略
//            dp[i][i] = true;
//        }
        int beginIndex = 0, endIndex = 0;            //记录最长的回文字符的初始和结束的index
        for (int j = 1; j < s.length(); j++) {          //注意i左j右!!!
            for (int i = 0; i < j; i++) {        //可以不等,如果等于就是等于1,没有扫描医院
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i > endIndex - beginIndex) {
                    beginIndex = i;
                    endIndex = j;
                }
            }
        }
        return s.substring(beginIndex, endIndex + 1);
    }

    /**
     * 题目2(leetcode 152): 乘积的最大子数组
     * 描述:给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * eg: 输入: [2,3,-2,4]  ==> 输出: 6
     * <p>
     * 思路01(动态规划):  记录prev的连续最大值和连续最小值;因为正负关系,所以要记录最大值和最小值
     * maxValue = max{cur,minValue*cur,MaxValue*cur}
     * minValue = min{cur,minValue*cur,MaxValue*cur}
     */
    public static int maxProduct(int[] nums) {            //动态规划     输入[-4,-3,-2] ?? 72
        if (nums == null || nums.length == 0) return -1;
        int maxValue = nums[0], minValue = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {      //0因为没有前面的值,所以从1开始计算
            int temp = maxValue;  //暂时保留maxValue,因为改变,要使用原来的值
            maxValue = Math.max(nums[i], Math.max(maxValue * nums[i], minValue * nums[i]));
            minValue = Math.min(nums[i], Math.min(temp * nums[i], minValue * nums[i]));
            res = Math.max(res, maxValue); //记录当前最大值,作为返回的值
        }
        return res;
    }

    /**
     * 题目3(leetcode 309): 最佳买卖股票时机含有冷冻期
     * 描述: 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * eg: 输入: [1,2,3,0,2] --> 输出: 3   对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 冷冻期是昨天刚刚卖出股票!!!
     * <p>
     * 思路01(这几个变量都是在结束之后): f[i][0]: 手上持有股票的最大收益    f[i][0]=max(f[i−1][0],f[i−1][2]−prices[i])
     * f[i][1]: 手上不持有股票，并且处于冷冻期(今天不能买入!!!)中的累计最大收益 f[i][1]=f[i−1][0]+prices[i]
     * f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益 f[i][2]=max(f[i−1][1],f[i−1][2])
     * 最后是max(f[n−1][0],f[n−1][1],f[n−1][2]) -->最后一天持有股票没有意义 max(f[n−1][1],f[n−1][2])
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;   //如果传入null或者传入0天,就返回0
        //每一天,利用三个值来维护(手上有股票/手上没有股票,处理冷冻期/手上没有股票,不处于冷冻期)
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];             //这是是二天以上,初始化第一天买入股票的情况,其他二个数初始化为0
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);     //手上有股票,昨天就持有,或者今天买的,那么昨天结束就不能处于冷冻期
            dp[i][1] = dp[i - 1][0] + prices[i];        //今天把股票抛出,说明昨天一定有股票!!!
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);            //今天没有操作而且没有股票,那么昨天一定没有股票,记录二个最大值
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);     //最后一天持持有股票没有意义
    }

    /**
     * 题目4(leetcode 221): 最大正方形
     * 描述: 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * eg: ：matrix = [
     * ["1","0","1","0","0"],
     * ["1","0","1","1","1"],
     * ["1","1","1","1","1"],
     * ["1","0","0","1","0"]
     * ] --> 输出4
     * <p>
     * 思路01: 暴力解
     * 思路02: 动态规划{
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1    这是状态转移方程
     * }
     */
    public int maximalSquare(char[][] matrix) {
        int resRow = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return resRow;
        int rows = matrix.length, columns = matrix[0].length;     //rows是行,columns是列
        int dp[][] = new int[rows][columns];      //初始化为0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]))+1;
                    resRow = Math.max(resRow, dp[i][j]);
                }
            }
        }
        return resRow * resRow;       //返回最大的面积
    }

    public static void main(String[] args) {
        System.out.println("题目2(leetcode 152): 乘积的最大子数组");
        System.out.println(maxProduct(new int[]{-4, -3, -2}));
    }
}
