package com.kwl.data01.swordOffer.Arrays题组;

/**
 * 剑指offer_数组_题组04
 *
 * @author kuang.weilin
 * @date 2021/6/30 22:22
 */
public class ArraysSword_04 {


    /**
     * 题目1(swordOffer 第67题): 把字符串转换成整数
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
     * 题目2(swordOffer 第51): 数组中的逆序对
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

}
