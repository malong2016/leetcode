package com.kwl.data01.myTest;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/7/3 0:08
 */
public class MyTest01 {

    public char firstUniqChar(String s) {
        //利用HashMap第二个值设置为是否包含，如果有包含就设置为false
        //然后扫描第一个true，就拿到结果
        Map<Character, Boolean> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), !map.containsKey(s.charAt(i))); //第一次是true
        }
        System.out.println("map = " + map);
        for(Character char01 : map.keySet()){
            if (map.get(char01).equals(true)){
                return char01;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
//        stack.isEmpty()
    }

}
