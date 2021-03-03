package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指offer_数组_题组06(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class ArraysSword_06 {

    /**
     * 题目1(swordOffer 面试题63):股票的最大利润
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
     * 题目2(swordOffer 面试题12):矩阵中的路径
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
                if (dfs(board,words,i,j,0)) return true;     //遍历所有可能的点
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
     * 题目3(swordOffer 面试题13): 机器人的运动范围
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
     * 题目3(swordOffer 面试题59): 队列的最大值
     * 描述: 给定一个数组和滑动窗口的大小,找出所有在滑动窗口的最大值
     * {2,3,4,2,6,2,5,1}滑动窗口3 --> 最大值分别是{4,4,6,6,6,5}
     * <p>
     * 思路01:暴力解,时间复杂度是o(n)
     * 思路02:利用一个双端队列Deque维护,1)仅仅含有窗口内的元素 2)非严格递减
     */
    public int[] maxSildingWindow(int[] nums, int k) {
        if (nums.length==0||k==0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();       //定义一个双端队列,removeFirst()/removeLast() 删除对首/对尾的元素
        int[] res = new int[nums.length - k + 1];      //滑动窗口k,那么有nums.length - k + 1种情况,每种情况存储一个最大值
        for (int j = 0,i=1-k; j <nums.length;i++, j++) {       //i在后,j在前
            if (i>0&&deque.peekFirst()==nums[i-1]) deque.removeFirst();    //删除deque中对应的num[i-1]
            while (!deque.isEmpty()&&deque.peekLast()<nums[j]) deque.removeLast(); //保持deque递减
            deque.addLast(nums[j]);
            if (i>=0) res[i] = deque.peekFirst();     //记录窗口的最大值
        }
        return res ;
    }




    public static void main(String[] args) {
        System.out.println("题目1(swordOffer 面试题63):股票的最大利润");
        System.out.println(maxDiff(new int[]{2, 1, 4}));
    }
}
