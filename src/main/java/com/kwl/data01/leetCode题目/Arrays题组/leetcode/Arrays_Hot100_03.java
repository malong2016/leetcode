package com.kwl.data01.leetCode题目.Arrays题组.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_03 {


    /**
     * 题目1(leetcode 283题): 移动零
     * 描述: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * eg:输入: [0,1,0,3,12]  --> 输出: [1,3,12,0,0]
     * 说明:  1必须在原数组上操作，不能拷贝额外的数组。
     * 2尽量减少操作次数。
     * <p>
     * 思路01(my): 暴力解,扫描到0,后面元素直接向前移动
     */
    public static void moveZeroes(int[] nums) {
    }

    /**
     * 题目3(leetcode 647题): 回文子串
     * 描述:给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * eg:输入："aaa" --> 输出：6  (6个回文子串: "a", "a", "a", "aa", "aa", "aaa")
     * <p>
     * 思路01(动态规划):
     */
    public int countSubstrings(String s) {
        //使用中心扩展,不断获取到中心,设置左右指针,向二端进行移动
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int left = i / 2, right = i / 2 + i % 2;            //设置左右指针
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }
        }
        return res;
    }
    /**
     * 题目3(leetcode 896题): 如果数组是单调递增或单调递减的，那么它是单调的。
     * 描述: 前后可以等于!!! 可以顺序或者到顺
     * eg: [1,2,2,3]  --> true   [1,3,2] -- false
     *
     * 思路01(一次遍历):先设置inc = true,des = true, 如果遇到arr[i]>arr[i+1] 就把inc设置为false,反之把des设置为false,
     * 最后inc||des,有一个为true就是true
     * 思路02(二次遍历): 同时验证是升序或者是降序,满足其一就可以return isSorted(A, true) || isSorted(A, false);
     */
    public boolean isMonotonic(int[] A) {
        if (A == null) return true;
        boolean inc = true,des = true;
        for (int i = 0; i < A.length-1; i++) {
            if (A[i] > A[i+1]) inc = false;
            if (A[i] < A[i+1]) des = false;
        }
        return inc || des;
    }


    public static void main(String[] args) {

    }
}
