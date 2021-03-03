package com.kwl.data01.leetCode题目.String题组.swordOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指offer_字符串(String)题组02
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class StringSword_02 {


    /**
     * 题目1(swordOffer 面试题38): 字符串的排列
     * 描述: 输入一个字符串,打印出该字符串中字符的所有排序。
     * 例如: 输入字符串abc,打印字符a,b,c所排列出来的所有字符串是
     * abc,acb,bac,bca,cab,cba
     * <p>
     * 思路01(leetcode):回溯法解决,排序数量是n!
     */
    List<String> res = new LinkedList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x){
        if (x == c.length-1){
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue; //如果重复就剪枝
            set.add(c[i]);
            swap(i,x);    //交换,将c[i]固定到x位
            dfs(x+1);
            swap(i,x);       //恢复交换 todo:???
         }
    }

    void swap(int a, int b) {       //交互索引a,b的值的
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

    /**
     * 题目2(swordOffer 面试题17): 打印从1到最大的n位数
     * <p>
     * 思路01(基础解): 最大Math.pow(10,n)-1
     * 思路02: todo 考虑大数!!!
     */
    public static int[] printToMaxOfNDigits(int n) {
        int max = (int) (Math.pow(10,n) - 1);
        int res[] = new int[max];
        for (int i = 0; i < max; i++)  res[i] = i+1;
        return res;
    }

    public static void main(String[] args) {
        System.out.println("题目1(swordOffer 面试题38): 字符串的排列");
        System.out.println("长度是: "+new StringSword_02().permutation("abcd").length);
        System.out.println(Arrays.toString(new StringSword_02().permutation("abcd")));
    }

}
