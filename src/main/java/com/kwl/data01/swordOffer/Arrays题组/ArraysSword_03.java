package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指offer_数组_题组05(每个题组是4个题目)
 * <p>
 * <p>
 * 题目1(swordOffer 面试题11):旋转数组的最小数字
 * 题目2(swordOffer 面试题44):数字序列中某一位数字
 * 题目3(swordOffer 面试题46):把数字翻译成字符串
 * 题目4(swordOffer 面试题47):礼物的最大价值
 * 题目5(swordOffer 面试题63):股票的最大利润
 * 题目6(swordOffer 面试题12):矩阵中的路径
 * 题目7(swordOffer 面试题13): 机器人的运动范围
 * 题目8(swordOffer 面试题59): 队列的最大值
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class ArraysSword_03 {

    /**
     * 题目1(swordOffer 面试题11):旋转数组的最小数字
     * 描述: 输入一个递增的arr,将最开始的若干个元素搬到数组的末尾
     * {3,4,5,1,2} 是 {1,2,3,4,5}的一个旋转数组
     * <p>
     * 思路01(leetcode 的二分法): 设置一个首尾指针i,j,在i!=j之前,用arr[(i+j)/2]和arr[j]比较
     * arr[mid]>arr[j] i=mid+1 (缩小范围)
     * arr[mid]<arr[j] j = mid(注意可能是mid,所以不能mid-1)
     * arr[mid]=arr[j] j--
     */
    public static int spanMin(int[] nums) {
        if (nums == null) return -1; //如果arr是null,就返回null
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] > nums[j]) i = mid + 1;     //缩小范围寻找,在下半区
            else if (nums[mid] < nums[j]) j = mid;
            else --j;
        }
        return nums[j];
    }

    /**
     * 题目2(swordOffer 面试题44):数字序列中某一位数字
     * 描述: 数字以0123456789101112131415....的序列化到一个字符序列中
     * 在这个序列中第5位(从0开始计算)是5,第13位是1,第19位是4.
     * 请写一个函数,求任意第n位对应的数字  //todo 找规律理解!!!
     */
    public static int digitAtIndex(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    /**
     * 题目3(swordOffer 面试题46):把数字翻译成字符串
     * 题目: 给定一个数字 0翻译为'a',1翻译为'b',11翻译为'l',25翻译为'z'
     * 一个数字经常有多个翻译,12258有五种翻译: bccfi,bwfi,bczi,mcfi,mzi
     * 编写一个函数用来计算一个数字有多少种不同的翻译情况
     * <p>
     * 思路01(leetcode 动态规划): f(i) = f(i-2)+f(i-1) 如果Xi-1Xi可以被翻译 f(i) = f(f-1),如果Xi-1Xi不能被翻译
     * 思路02(数字求余): todo等下
     */
    public static int getTranslationCount(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];   //这里要是最后一位对应num,考虑0就是length+1,不考虑就是length
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            String temp = str.substring(i - 2, i);
            if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[str.length()];
    }

    /**
     * 题目4(swordOffer 面试题47):礼物的最大价值
     * 描述: 给定一个二维数组m*n,每一格都放有一个礼物,每一礼物都有一定的价值,
     * 从左下角-->每次向右或者向下移动一格-->到达右下角,求最多能拿到多少价值的礼物?
     * <p>
     * {1,  10,  3,   8},
     * {12,  2,  9,   6},
     * {5,  7,   4,   11},
     * {3,  7,  16,   5}
     * <p>
     * 思路01(leetcode 动态规划): f(i,j) = max|f(i,j-1),f(i-1,j)|+grid(i,j)   注意:(i,j)是坐标是最后达到的地方!!!
     * 思路02(递归): todo 去评论区看看!!!
     */
    public static int getMaxValueSolution(int[][] grib) {
        int m = grib.length, n = grib[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;      //第一行第一列的情况
                if (i == 0) grib[i][j] += grib[i][j - 1];     //第一行的情况
                else if (j == 0) grib[i][j] += grib[i - 1][j];
                else grib[i][j] += Math.max(grib[i][j - 1], grib[i - 1][j]);
            }
        }
        return grib[m - 1][n - 1];
    }

    public static int maxValue(int[][] grid) {           //不需要每次循环都要判断!!!!执行效率更高
        int m = grid.length, n = grid[0].length;
        for (int j = 1; j < n; j++) // 初始化第一行,第一行的长度由多少列决定(grid[0].length)！！！
            grid[0][j] += grid[0][j - 1];
        for (int i = 1; i < m; i++) // 初始化第一列,第一列的长度由多少行决定(grid.length)
            grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        return grid[m - 1][n - 1];
    }

    public static int maxValueRecur(int[][] grid) {   //方法三:递归法.leetcode是超时的
        return recur(grid, grid.length - 1, grid[0].length - 1);
    }

    public static int recur(int[][] grid, int m, int n) {
        if (m == 0 && n == 0) return grid[m][n];
        else if (m == 0) return grid[m][n] + recur(grid, m, n - 1);   //第一行的情况
        else if (n == 0) return grid[m][n] + recur(grid, m - 1, n);   //第一列的情况
        else return grid[m][n] + Math.max(recur(grid, m - 1, n), recur(grid, m, n - 1));
    }


    /**
     * 题目5(swordOffer 面试题63):股票的最大利润
     * 股票价格按照先后顺序放在arr中{9,11,8,5,7,12,16,14} 注意股票是先后时间
     * 只能在时间前买入,后面卖出才能有最大利润,这个股票最大利润是16-5=11
     * <p>
     * 思路: 动态规划
     */
    public static int maxDiff(int[] numbers) {
        if (numbers == null || numbers.length <= 1) return 0;  //传入null或者长度小于1的数组,无法交易股票返回-1
        int profit = 0;
        int cost = Integer.MAX_VALUE;      //cost是保存前面数组的最小值,也是股票买入的最低值！！！
        for (int number : numbers) {
            cost = Math.min(cost, number);
            profit = Math.max(profit, number - cost); //当前最大利润和默认的saveLastMaxDiff比较,取较大值
        }
        return profit;
    }

    /**
     * 题目6(swordOffer 面试题12):矩阵中的路径
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
    public static boolean hasPath(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;     //遍历所有可能的点
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }

    /**
     * 题目7(swordOffer 面试题13): 机器人的运动范围
     * 描述: 地上有一个m*n的方格。一个机器人在坐标为(0,0)的格子开始运动,每次可以
     * 左,右,上,下的运动一格,但是不能进入行坐标和列坐标的数位大于k的格子。
     * eg: 当k为18的时候,机器人能进入(35,38),因为是3+5+3+7=18,但是不能进入(35,38)
     * 因为是3+5+3+8 = 19
     * 设计一个函数,求机器人能到达多少个格子.
     * <p>
     * 思路01(leetcode): 使用回溯法(dfs)解决问题
     */
    public static int movingCount(int[][] nums) {
        return 0;
    }

    /**
     * 题目8(swordOffer 面试题59): 滑动窗口的最大值
     * 描述: 给定一个数组和滑动窗口的大小,找出所有在滑动窗口的最大值
     * {2,3,4,2,6,2,5,1}滑动窗口3 --> 最大值分别是{4,4,6,6,6,5}
     * <p>
     * 思路01:暴力解,时间复杂度是o(n)
     * 思路02:利用一个双端队列Deque维护,1)仅仅含有窗口内的元素 2)非严格递减  //todo这个回溯法不算很难
     */
    public int[] maxSildingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();       //定义一个双端队列,removeFirst()/removeLast() 删除对首/对尾的元素
        int[] res = new int[nums.length - k + 1];      //滑动窗口k,那么有nums.length - k + 1种情况,每种情况存储一个最大值
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {       //i在后,j在前
            if (i > 0 && deque.peekFirst() == nums[i - 1]) deque.removeFirst();    //删除deque中对应的num[i-1]
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) deque.removeLast(); //保持deque递减
            deque.addLast(nums[j]);
            if (i >= 0) res[i] = deque.peekFirst();     //记录窗口的最大值
        }
        return res;
    }


}
