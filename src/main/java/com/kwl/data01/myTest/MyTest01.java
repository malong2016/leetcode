package com.kwl.data01.myTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/7/3 0:08
 */
public class MyTest01 {

    //默写,有效的括号

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     *
     * 1 左括号必须用相同类型的右括号闭合。
     * 2 左括号必须以正确的顺序闭合。
     *
     * 思路01：使用一个Stack进行判断，入队右括号")}]"就和栈顶进行比较，相同就出队，不同就不满足括号匹配
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            }else{
                if (stack.pop() != s.charAt(i)) return false;
            }
        }
        return stack.isEmpty();
    }
}
