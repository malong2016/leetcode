package com.kwl.data01.swordOffer.回溯_递归;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author kuang.weilin
 * @date 2021/7/2 21:32
 */
public class dfsSword_01 {


    /**
     * 题目01(swordOffer 第38题): 字符串的排列
     * 描述: 输入一个字符串,打印出该字符串中字符的所有排序。
     * 例如: 输入字符串abc,打印字符a,b,c所排列出来的所有字符串是
     * abc,acb,bac,bca,cab,cba
     * <p>
     * <p>
     * 思路01(K神): 常规进行回溯,注意我们把s转化为chars[],然后进行交换，最后添加又把chars[]转化回来
     * 思路02(评论区高赞): 利用visit和set去重
     */
    public String[] permutationAndVisit(String s) {                //visit方法
        Set<String> resSet = new HashSet<>();               //使用Set进行添加，会携带去重效果
        boolean[] visit = new boolean[s.length()];       //使用visit标记访问
        dfs("", s, visit, resSet);
        return resSet.toArray(new String[resSet.size()]);
    }

    void dfs(String temp, String s, boolean[] visit, Set<String> resSet) {
        if (temp.length() == s.length()) {
            resSet.add(temp);     //如果temp长度符合就添加进来，注意这里set去重
            return;     //temp到达了s的长度，直接返回，没有必要下面的循环
        }
        //注意,这个temp每次都是新的，所以和我们一般的path不一样，path的话，添加了其他的元素，必须回溯.这里不需要回溯
        for (int i = 0; i < s.length(); i++) {
            if (visit[i]) continue;        //如果已经访问，就直接跳过
            visit[i] = true;
            dfs(temp + s.charAt(i), s, visit, resSet);
            visit[i] = false;
        }
    }

    public String[] permutation(String s) {           //传统的交换法则
        char[] chars = s.toCharArray();
        List<String> res = new LinkedList<>();
        dfs(0, res, chars);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int index, List<String> res, char[] chars) {       //这里是使用char[]比较好转化为string,但是要swap函数
        if (index == chars.length) {
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
