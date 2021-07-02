package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指offer_数组_题组03(每个题组是4个题目)
 * <p>
 * <p>
 * 题目1(swordOffer 面试题11):旋转数组的最小数字
 * 题目2(swordOffer 面试题44):数字序列中某一位数字
 * 题目4(swordOffer 面试题47):礼物的最大价值
 * 题目5(swordOffer 面试题63):股票的最大利润
 * 题目6(swordOffer 面试题12):矩阵中的路径
 * 题目7(swordOffer 面试题13): 机器人的运动范围
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
     * 思路01(leetcode 的二分法):  本质上就是寻找最小值（可能存在重复值）
     * 设置一个首尾指针i,j,在i!=j之前,用arr[(i+j)/2]和arr[j]比较
     * arr[mid]>arr[j] i=mid+1 (缩小范围)
     * arr[mid]<arr[j] j = mid(注意可能是mid,所以不能mid-1)
     * arr[mid]=arr[j] j--
     */
    public static int spanMin(int[] nums) {
        if (nums == null) return -1; //如果arr是null,就返回null
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) low = mid + 1;    //上半区
            else if (nums[mid] < nums[high]) high = mid;  //下半区，可能就是mid
            else --high;         //向下逼近
        }
        return nums[high];
    }

    /**
     * 题目2(swordOffer 面试题44):数字序列中某一位数字
     * 描述: 数字以0123456789101112131415....的序列化到一个字符序列中
     * 在这个序列中第5位(从0开始计算)是5,第13位是1,第19位是4.
     * 请写一个函数,求任意第n位对应的数字
     */
    public static int digitAtIndex(int n) {
        int digit = 1;            //是几位数(位数)
        long start = 1;          //从哪一位开始 (一定是long)
        long count = 9;         //本组存在几位数 (1~9, 10~99, 100~999)(一定是long)
        while (n > count) {
            n -= count;
            digit += 1;     //1位数, 2位数, 3位数
            start *= 10;   //1 10 100 1000 ,9*start就是本组的个数
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }


    /**
     * 题目5(swordOffer 面试题12):矩阵中的路径
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
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k])
            return false;    //越界是直接返回false
        if (k == word.length - 1) return true;
        board[i][j] = '\0';    //设置为空字符
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k]; //board[i][j]一定是等于word[k]的
        return res;
    }

    /**
     * 题目6(swordOffer 面试题13): 机器人的运动范围
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
     * 题目7(swordOffer 面试题59): 滑动窗口的最大值
     * 描述: 给定一个数组和滑动窗口的大小,找出所有在滑动窗口的最大值
     * {2,3,4,2,6,2,5,1}滑动窗口3 --> 最大值分别是{4,4,6,6,6,5}    //从k开始都有一个滑动窗口，res的长度是length-k+1
     * <p>
     * 思路01:暴力解,时间复杂度是o(n)
     * 思路02:利用一个双端队列Deque维护,
     * 1)仅仅含有窗口内的元素: 每轮窗口滑动移除了元素 nums[i - 1]nums[i−1] ，需将 dequedeque 内的对应元素一起删除。
     * 2)非严格递减: 每轮窗口滑动添加了元素 nums[j + 1]nums[j+1] ，需将 dequedeque 内所有 < nums[j + 1]<nums[j+1] 的元素删除。
     * 3) 每次都是队首就是最大值
     */
    public int[] maxSildingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        LinkedList<Integer> linkedList = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 1 - k, j = 0; j < nums.length; i++, j++) {
            if (i > 0 && linkedList.peekFirst() == nums[i - 1])
                linkedList.removeFirst();    //如果num[i-1](对应滑动过的窗口)和队列中队首相等就删除
            while (!linkedList.isEmpty() && linkedList.peekLast() < nums[j])
                linkedList.removeLast(); //保持linkedList递减,删除队列中所有比Num[j]小的数
            linkedList.addLast(nums[j]);
            if (i >= 0) res[i] = linkedList.peekFirst();     //记录窗口的最大值
        }
        return res;
    }

}
