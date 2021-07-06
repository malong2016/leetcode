package com.kwl.data01.swordOffer.回溯_递归;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/7/2 21:32
 */
public class dfsSword_01 {


    /**
     * 题目1(swordOffer 第38题): 字符串的排列
     * 描述: 输入一个字符串,打印出该字符串中字符的所有排序。
     * 例如: 输入字符串abc,打印字符a,b,c所排列出来的所有字符串是
     * abc,acb,bac,bca,cab,cba
     * <p>
     * <p>
     * 思路01(K神): 常规进行回溯,注意我们把s转化为chars[],然后进行交换，最后添加又把chars[]转化回来
     * 思路02(评论区高赞): 利用visit和set去重  todo visit!!![]
     */
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        List<String> res = new LinkedList<>();
        dfs(0, res, chars);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int index, List<String> res, char[] chars) {
        if (index == chars.length - 1) {
            res.add(String.valueOf(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (set.contains(chars[i])) continue; //也可以排序去重
            set.add(chars[i]);
            swap(chars, i, index);
            dfs(index + 1, res, chars);
            swap(chars, i, index);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
