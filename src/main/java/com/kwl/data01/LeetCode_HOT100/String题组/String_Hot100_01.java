package com.kwl.data01.LeetCode_HOT100.String题组;

import java.util.Stack;

/**
 * String题组-01
 *
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class String_Hot100_01 {


    /**
     * 题目01:有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 1)左括号必须用相同类型的右括号闭合。
     * 2)左括号必须以正确的顺序闭合。
     *
     * 思路01(常规解法): 利用Stack
     * 思路02(巧解): 循环leng/2次，每次都是替换{}/[]/()到""
     */
    public boolean isValid01(String s) {               //stack常规法
        if (s == null || s.length() %2!=0) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            }else if (s.charAt(i) == '{'){
                stack.push('}');
            }else if (s.charAt(i) == '['){
                stack.push(']');
            } else if (stack.isEmpty() || s.charAt(i)!=stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
    public boolean isValid02(String s) {        //巧解，循环length/2次，每次都是替换{}/[]/()到""
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
          s =  s.replace("{}", "").replace("[]", "").replace("()", "");
        }
        return s.length()==0;
    }
}
