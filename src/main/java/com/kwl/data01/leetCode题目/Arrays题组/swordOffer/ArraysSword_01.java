package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

import java.util.Arrays;

/**
 * 剑指offer_数组_题组01(每个题组是4个题目,本组二个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class ArraysSword_01 {

    /**
     * 题目1(swordOffer 面试题21):输入一个整数数组,一个方法让奇数在arr的前半部分,偶数在arr的后半部分
     * <p>
     * 思路01:暴力解,就是顺序扫描数组,遇到偶数就让前面的数前面向后移动一位,在将这个数插入到末尾
     * 时间复杂度是0(n2)不推荐
     * 思路02:设置一个首尾指针,pEnd>pBegin之前,pBegin从头开始扫描,pEnd从尾部向前扫描,pBegin扫描到偶数,pEnd扫描到奇数
     * 就交互数组
     */
    public static void reorderOddEven(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int pBegin = 0, pEnd = arr.length - 1;         //定义首尾指针
        while (pBegin < pEnd) {
            while (pBegin < pEnd && !isEven(arr[pBegin])) pBegin++;     //如果是奇数就前移动,直到找到偶数
            while (pBegin < pEnd && isEven(arr[pEnd])) pEnd--;         //如果是偶数就后移动,直到找到奇数

            //交换,注意:交互之后pBegin又偶数变成奇数,又可以移动,pEnd也一样
            if (pBegin < pEnd) {          //先判断,防止越界的情况
                int temp = arr[pBegin];
                arr[pBegin] = arr[pEnd];
                arr[pEnd] = temp;
            }
        }
    }

    public static boolean isEven(int n) {        //判断是否是偶数,也是一种抽取,如果面试题目改变负数和非负数等等都是可以在这里改变
        return n % 2 == 0;
    }

    /**
     * 题目1(swordOffer 面试题53):数字在排序数组中出现的次数
     * 描述: 统计一个数字在排序数组中出现的次数。例如,输入排序数组{1,2,3,3,3,3,4,5}和数字3,数组3出现了四次,因此输出4
     * <p>
     * 思路01(基础解): 先二分查找到3,在左右扫描统计3出现的次数,时间复杂度是o(n)
     * 思路02(最优解,时间复杂度o(log2n)): 先二分查到到3,如果k左边第一个元素不是k(或者改元素下标是0),那么这个元素就是第一个k,返回这个元素的index
     * 左边存在,在递归找左边,找到满足要求
     */
    public static int getFirstK(int[] arr, int k, int start, int end) { //递归开始和结束的位置
        if (start > end) return -1;       //不存在k,返回-1
        int mid = (start + end) / 2;
        if (arr[mid] == k) {
//            if (mid > 0 && arr[mid - 1] != k || mid == 0) return mid;
            if (mid == 0 || arr[mid - 1] != k) return mid;
            else end = mid - 1;    //向下找
        } else if (arr[mid] > k) {          //左半区找
            end = mid - 1;
        } else {                            //右半区找
            start = mid + 1;
        }
        return getFirstK(arr, k, start, end);
    }

    public static int getLastK(int[] arr, int k, int start, int end) {      //理由同上
        if (start > end) return -1;       //不存在k,返回-1
        int mid = (start + end) / 2;
        if (arr[mid] == k) {
            if (mid == arr.length - 1 || arr[mid + 1] != k ) return mid; //不能等于
            else start = mid + 1;    //向上找
        } else if (arr[mid] > k) {          //左半区找
            end = mid - 1;
        } else {                            //右半区找
            start = mid + 1;
        }
        return getLastK(arr, k, start, end);
    }

    public static int getNumberOfK(int[] arr, int k) {
        int number = 0;
        if (arr != null) {
            int firstK = getFirstK(arr, k, 0, arr.length - 1);
            int lastK = getLastK(arr, k, 0, arr.length - 1);

            if (firstK > -1 && lastK > -1) number = lastK - firstK + 1;
        }
        return number;        //注意arr如果是null,就返回初始化0
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 2, 2, 3, 5, 7};
//        reorderOddEven(arr);             //
//        System.out.println(Arrays.toString(arr));
        System.out.println("测试数字在排序数组中出现的次数");
        System.out.println(getNumberOfK(arr, 2));
    }
}
