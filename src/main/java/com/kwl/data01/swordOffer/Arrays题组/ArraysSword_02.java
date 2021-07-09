package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author kuang.weilin
 * @date 2021/2/17
 */
public class ArraysSword_02 {


    /**
     * 题目01(swordOffer 第39题): 数组中出现次数超过一半的数字
     * 描述: 数组中出现次数超过数组长度一半，请找出这个数字(一定存在)
     * <p>
     * 思路01:利用HashMap逐个统计,最后遍历
     * 思路02(最优解):摩尔投票法找出众数,设置一个x,扫描下一个,如果等于vote++,不等于vote--
     * vote变成0之后,换数
     */
    public int moreThanHalfNum(int[] arr) {    //思路2
        int res = 0, votes = 0, count = 0;
        for (int num : arr) {
            if (votes == 0) res = num;
            votes += num == res ? 1 : -1;       //扫描的数等于x,votes+1,否则vote-1
        }
        //上面这个只是找出众数,验证这个众数是否超过一半(如果必存在就不需要验证!!!)
        for (int i : arr) {
            if (i == res) count++;
        }
        return count > arr.length / 2 ? res : 0;     //如果没有超过一半的众数就返回0
    }

    /**
     * 题目02(swordOffer 第40题): 最小的k个数
     * 输入n个整数,找出其中最小的k个数。例如，输入4,5,1,6,2,7,3,8这8个数字，最小的4个数字是1、2、3、4
     * <p>
     * 思路01:先排序,前k个就是最小的k个数
     * 思路02: 堆排序方法
     * 思路03：快速排序方法
     */
    public int[] getLeastNumbers(int[] arr, int k) { //手写堆排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();       //这是小顶推
        for (int i : arr) priorityQueue.offer(i);      //入队
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = priorityQueue.poll(); //前k个元素出队,得到就是最小的k个数
        return res;
    }

    /**
     * 题目03(swordOffer 第29题): 顺时针打印矩阵
     * eg:
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     * 打印的结果是: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10  (最大环顺时针,第二大环顺时针....)
     * 打印方向是: 从左到右-->从上到下-->从右到左-->从下向上 顺时针打印
     * <p>
     * 思路01(leetcode):模拟 t(上) b(下) l(左) r(右) 上下左右四个边界
     */
    public int[] printMatrix(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int t = 0, b = matrix.length - 1, x = 0;               //设置上下的边界
        int l = 0, r = matrix[0].length - 1;                   //设置左右的边界
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left(左) to right(右). 固定top(上)
            if (++t > b) break;          //注意最后t和b l和f相遇的时候，这行或者这列是没有打印，打印完成这行/列，就是越界，所以结束循环
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top(上) to bottom(下). 固定right(右)
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right(右) to left(左). 固定bottom(底部)
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom(下) to top(上). 固定left(左)
            if (++l > r) break;
        }
        return res;
    }


    /**
     * 题目04(swordOffer 第03题): 数组中重复的数字(可以修改数组)
     * 描述: 在一个长度为n的数组中所有数字都是在0~n-1范围内。数组中某些数字是  （解题思路num[i] = i一一对应，找出第二个一一对应就是重复值）
     * 重复的，但是不知道有几个数字是重复的，也不知道每个数字重复了几次。请
     * 找出数组中任意的一个重复的数字。例如，如果输入长度为为7的数组{2,3,1,0,2,5,3}
     * 那么对应输出的重复的数字是2或者3
     * <p>
     * <p>
     * 思路01(leetcode): HashSet不断的加入,遇到重复的元素就输出
     * 思路02(leetcode): 原地交换,将这个元素换到对应的index下（value = 2,换到index=2下）,如果遇到arr[num[i]]=num[i]就输出num[i]
     */
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }


    /**
     * 题目05(swordOffer 第04题): 二维数组中的查找
     * 描述: 在一个二维数组中,每一行都是按照从左到右的递增顺序排列,每一列都是按照从上到下递增顺序排序。
     * 请完成一个函数,输入这样的一个二维数组和一个整数,判断数组中是否含有这样的整数
     * eg:
     * {1, 2, 8,   9}
     * {2, 4, 9,  12}
     * {4, 7, 10, 13}
     * {6, 8, 11, 15}
     * <p>
     * 思路01: 从左下角(i,j)开始遍历,如果该值>targetValue,i--,如果该值<targetValue,j++
     */
    public boolean findNumberIn2DArray(int[][] arr, int targetValue) {
        if (arr == null) return false;
        int i = arr.length - 1, j = 0;
        while (i >= 0 && j < arr[0].length) {
            if (arr[i][j] > targetValue) i--;
            else if (arr[i][j] < targetValue) j++;
            else return true;
        }
        return false;
    }

    /**
     * 题目06(swordOffer 第67题): 把字符串转换成整数
     * <p>
     * 说明: 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * <p>
     * <p>
     * 思路:
     * 1) 越界情况统计是
     * res>bndry: 情况一：执行拼接10×res≥2147483650越界
     * res=bndry,x>7: 情况一：执行拼接10×res≥2147483650越界
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) return 0;
        int res = 0, binay = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;   //sign就是判断正负关系,i是从那么开始扫描,默认是正数那么就是从Index = 1 开始扫描
        if (chars[0] == '-') sign = -1;     //表明这个数是负数
        else if (chars[0] != '+') i = 0; //如果不是正数,那么就是从0开始扫描,互斥事件
        for (int j = i; j < chars.length; j++) {
            if (chars[j] < '0' || chars[j] > '9') break;
            if (res > binay || res == binay && chars[j] > '7')
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (chars[j] - '0'); //累加到返回值
        }
        return sign * res;
    }

    /**
     * 题目07(swordOffer 第51): 数组中的逆序对
     * 描述: 在数组中的两个数字，如果前面一个数字大于后面的数字，
     * 则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     * <p>
     * eg: 输入: [7,5,6,4]
     * 输出: 5
     * <p>
     * 思路01: 暴力解，双循环，超时
     * 思路02: 二路归并排序
     */
    public int reversePairs(int[] nums) { //暴力解
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) res++;     //如果前面是大于后面的那么满足逆序对
            }
        }
        return res;
    }

    /**
     * 题目08(swordOffer 第66题): 构建乘积数组
     * <p>
     * 描述: 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
     * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     * <p>
     * 思路01：第一轮循环求出的b[i]是包括a[0] * a[1] * a[i - 1]
     * 第二轮反向循环求出的temp是a[a.length - 1] * a[a.length - 2] * a[i+2] * a[i + 1]
     * b[i] = b[i] * temp 就是返回的结果
     */
    public int[] constructArr(int[] a) {
        if (a.length == 0) return new int[0];
        int[] b = new int[a.length];
        b[0] = 1;
        int temp = 1;

        //计算b: b[i] 代表是a[0,...i - 1]的乘积
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }

        for (int i = a.length - 1; i >= 0; i++) {
            b[i] = b[i] * temp;
            temp = temp * a[i];
        }
        return b;
    }
}
