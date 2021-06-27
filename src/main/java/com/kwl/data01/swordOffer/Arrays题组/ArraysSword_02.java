package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指offer_数组_题组02(每个题组是8个题目,本组8个题目)
 *
 * 题目1(swordOffer 面试题39): 数组中次数超过一半的数字
 *  题目2(swordOffer 面试题40): 最小的k个数
 *  题目3(swordOffer 面试题42): 连续子数组的最大和
 *  题目4(swordOffer 面试题29): 顺时针打印矩阵(是一个二维数组)
 *  题目5(swordOffer 面试题3题目一): 数组中重复的数字(可以修改数组)
 *  题目6(swordOffer 面试题3题目二): 数组中重复的数字(不修改数组)  todo
 *  题目6(swordOffer 面试题3题目二): 数组中重复的数字(不修改数组)  todo
 *  题目7(swordOffer 面试题4): 二维数组中的查找
 *  题目8(swordOffer 面试题19):正则表达式的匹配

 * @author kuang.weilin
 * @date 2021/2/17
 */
public class ArraysSword_02 {


    /**
     * 题目1(swordOffer 面试题39): 数组中次数超过一半的数字
     * 描述: 数组中出现次数超过数组长度一半，请找出这个数字(一定存在)
     * <p>
     * 思路01:利用HashMap逐个统计,最后遍历
     * 思路02(最优解):摩尔投票法找出众数,设置一个x,扫描下一个,如果等于vote++,不等于vote--
     * vote变成0之后,换数
     *
     */
    public static int moreThanHalfNum(int[] arr) {    //思路2
        int x = 0, votes = 0, count = 0;
        for (int num : arr) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;       //扫描的数等于x,votes+1,否则vote-1
        }
        //上面这个只是找出众数,验证这个众数是否超过一半(如果必存在就不需要验证!!!)
        for (int i : arr) {
            if (i == x) count++;
        }
        return count > arr.length / 2 ? x : 0;     //如果没有超过一半的众数就返回0
    }

    /**
     * 题目2(swordOffer 面试题40): 最小的k个数
     * 输入n个整数,找出其中最小的k个数。例如，输入4,5,1,6,2,7,3,8这8个数字，最小的4个数字是1、2、3、4
     * <p>
     * 思路01:先排序,前k个就是最小的k个数
     * 思路02: 堆排序方法
     * 思路03：快速排序方法
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) return vec;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {      //这是java堆排序的数据结构
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {    //将前k个数入堆
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {      //如果栈顶大于元素,出队,将比较小的数入队
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            vec[i] = queue.poll();
        }
        return vec;
    }


    /**
     * 题目3(swordOffer 面试题42): 连续子数组的最大和
     * 描述: 输入一个整型数组，数组中存在正数和负数。数组中的一个或者连续多个整数
     * 组成一个子数组。求所有子数组的和的最大值。要求时间复杂度是o(n)
     * eg: 输入{1,-2,3,10,-4,7,2,-5} 最大子数组是{3,10,-4,7,2}
     * 输出该子数组的和是18
     * <p>
     * 思路01(leetcode): 利用动态规划,先求出上一个i-1的最大子数列,和0进行比较,小于等于0就取max(i-1)
     * 大于0就取本值
     */
    public static int FindGreatestSumOfSumOfSubArray(int[] arr) {
        int res = arr[0];    //默认是res是arr[0]
        for (int i = 1; i < arr.length; i++) {
            arr[i] += Math.max(arr[i - 1], 0);      //当前index最大子数组之和,比较上一个,如果为0,就舍弃,为正数就接着
            res = Math.max(arr[i], res);
        }
        return res;
    }

    /**
     * 题目4(swordOffer 面试题29): 顺时针打印矩阵(是一个二维数组)
     * eg:
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     * 打印的结果是: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10  (最大环顺时针,第二大环顺时针....)
     * 打印方向是: 从左到右-->从上到下-->从右到左-->从下向上 顺时针打印
     *
     * 思路01(leetcode):模拟 t(上) b(下) l(左) r(右) 上下左右四个边界
     */
    public static int[] printMatrix(int[][] matrix){
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left(左) to right(右). 固定top(上)
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top(上) to bottom(下). 固定right(右)
            if(l > --r) break;
            for(int i = r; i >= l; i--)  res[x++] = matrix[b][i]; // right(右) to left(左). 固定bottom(底部)
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom(下) to top(上). 固定left(左)
            if(++l > r) break;
        }
        return res;
    }


    /**
     * 题目5(swordOffer 面试题3题目一): 数组中重复的数字(可以修改数组)
     * 描述: 在一个长度为n的数组中所有数字都是在0~n-1范围内。数组中某些数字是
     * 重复的，但是不知道有几个数字是重复的，也不知道每个数字重复了几次。请
     * 找出数组中任意的一个重复的数字。例如，如果输入长度为为7的数组{2,3,1,0,2,5,3}
     * 那么对应输出的重复的数字是2或者3
     * <p>
     * 思路01(leetcode): HashSet不断的加入,遇到重复的元素就输出
     * 思路02(leetcode): 原地交换,将这个元素换到对应的index下（value = 2,换到index=2下）,如果遇到arr[num[i]]=num[i]就输出num[i]
     */
    public static int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i]; //这说明对应的位置上存在num[i] = i
            int temp = nums[i];       //同时记录下a的值和b的下标
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;     //找不到就返回-1
    }

    /**
     * 题目6(swordOffer 面试题3题目二): 数组中重复的数字(不修改数组)  todo
     * 描述: 在一个长度为n的数组中所有数字都是在0~n-1范围内。数组中某些数字是
     * 重复的，但是不知道有几个数字是重复的，也不知道每个数字重复了几次。请
     * 找出数组中任意的一个重复的数字。例如，如果输入长度为为7的数组{2,3,1,0,2,5,3}
     * 那么对应输出的重复的数字是2或者3
     */

    /**
     * 题目7(swordOffer 面试题4): 二维数组中的查找
     * 描述: 在一个二维数组中,每一行都是按照从左到右的递增顺序排列,每一列都是按照从上到下递增顺序排序。
     * 请完成一个函数,输入这样的一个二维数组和一个整数,判断数组中是否含有这样的整数
     * eg:
     * {1, 2, 8,   9}
     * {2, 4, 9,  12}
     * {4, 7, 10, 13}
     * {6, 8, 11, 15}
     * <p>
     * 思路01: 从有下角(i,j)开始遍历,如果该值>targetValue,i--,如果该值<targetValue,j++
     */
    public static boolean findNumberIn2DArray(int[][] arr, int targetValue) {
        if (arr==null) return false;        //传入null,返回false
        int i = arr.length - 1, j = 0;
        while (i >= 0 && j < arr[0].length) {
            if (arr[i][j] > targetValue) i--;           //二维数组第一个是行,第二个是列
            else if (arr[i][j] < targetValue) j++;
            else return true;
        }
        return false;
    }

    /**
     * 题目8(swordOffer 面试题19):正则表达式的匹配
     * 描述: 请实现一个函数来匹配,'.'代表任意一个字符,'*'代表0个后者任意长度的字符
     * 判断二个字符是否匹配, 'a.a'和'ab*ac*a'匹配    'aa.a'和'ab*a'不匹配
     *
     * 思路01:动态规划 todo理解
     */
    public static boolean match(String s,String p){
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for(int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        // 状态转移
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(p.charAt(j - 1) == '*') {
                    if(dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if(dp[i][j - 1]) dp[i][j] = true;                                       // 2.
                    else if(dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 3.
                    else if(dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 4.
                } else {
                    if(dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if(dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }



}
