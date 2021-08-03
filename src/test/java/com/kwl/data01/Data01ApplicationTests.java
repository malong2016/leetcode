package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;


@SpringBootTest
class Data01ApplicationTests {

    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {             //这个')'去和'('中进行匹配，这个也是只能逼出一个!!!
                stack.pop();
                if (stack.isEmpty()) stack.push(i); //栈为空，就把)的index入栈(最后一个没有被匹配到的)
                else res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }

    @Test
    void test() {
        Stack<String> stack = new Stack<>();
        stack.push("张三");
        stack.push("李四");
        stack.push("李四01");
        System.out.println("stack = " + stack);
        System.out.println(stack.get(0));
        System.out.println(stack.search("李四01"));
        System.out.println(stack.search("张三"));
    }
}
