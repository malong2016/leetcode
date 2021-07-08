package com.kwl.data01.HOT100.回溯_递归;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kuang.weilin
 * @date 2021/7/6 23:31
 */
public class Dfs_Hot100_02 {

    /**
     * 题目01(leetcode 第17题): 电话号码的字母组合
     * 描述: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    Map<Character, String> phoneMap = new HashMap<Character, String>(){{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    List<String> res = new ArrayList<String>();
    public List<String> letterCombinations(String digits) { //回溯法(官方)
        if (digits.length() == 0) return res;
        dfs01(digits, 0, new StringBuffer());
        return res;
    }

    public void dfs01(String digits, int index,StringBuffer combination){
        if (index == digits.length()) res.add(combination.toString());
        else {
            char digit = digits.charAt(index);
            String letters =   phoneMap.get(digit);
            int lettersConut = letters.length();
            for (int i = 0;i < lettersConut;i++){
                combination.append(letters.charAt(i));
                dfs01(digits,index+1,combination);
                combination.deleteCharAt(combination.length() - 1);
            }
        }
    }
}
