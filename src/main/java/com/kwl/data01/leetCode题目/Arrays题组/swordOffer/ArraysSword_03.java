package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指offer_数组_题组03(每个题组是4个题目,本组二个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/17
 */
public class ArraysSword_03 {


    /**
     * 题目1(swordOffer 面试题39): 数组中次数超过一半的数字
     * 描述: 数组中出现次数超过数组长度一半，请找出这个数字(一定存在)
     * <p>
     * 思路01:利用HashMap逐个统计,最后遍历
     * 思路02(最优解):摩尔投票法找出众数,设置一个x,扫描下一个,如果等于vote++,不等于vote--
     * vote变成0之后,换数
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


    public static void main(String[] args) {
//        System.out.println("题目1(swordOffer 面试题39题目1): 数组中次数超过一半的数字");
//        System.out.println(moreThanHalfNum(new int[]{1, 2, 2,3,4,5,6}));

//        System.out.println("题目2(swordOffer 面试题40): 最小的k个数:");
//        System.out.println(Arrays.toString(getLeastNumbers(new int[]{1, 23, 2, 4}, 3)));

        System.out.println("题目4(swordOffer 面试题29): 顺时针打印矩阵(是一个二维数组)");
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        System.out.println(Arrays.toString(printMatrix(matrix)));

    }
}
