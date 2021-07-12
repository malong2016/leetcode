package com.kwl.data01.HOT100.String题组;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/5 23:08
 */
public class String_Hot100_02 {

    /**
     * 题目01（leetcode 301题）:删除无效的括号
     * 描述: 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * eg:
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * <p>
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     *
     * 思路01: BFS,注意可以控制本长度节点，也可以不用控制
     * 思路02: todo 删除无效的括号，dfs
     */
    public List<String> removeInvalidParentheses(String s) {
        int len = s.length();
        LinkedList<String> res = new LinkedList<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);      //将s入队
        boolean flag = false;
        //注意，这里可以不使用size队本长度节点控制(也可以判断,不过会延长时间)，因为本层如果符合，下层一定是不符合的！！偶数—>奇数
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {       //判断本长度!也可以不判断
                String pollStr = queue.poll();
                if (isValid(pollStr)) {
                    res.addLast(pollStr);
                    flag = true;        //将本次的flag设置为true
                }
                if (flag) {      //如果检测到flag为true,跳出本次循环
                    continue;
                }
                for (int i = 0; i < pollStr.length(); i++) {         //长度为n-1截取的全部入队
                    String str = "";
                    if (pollStr.charAt(i) == '(' || pollStr.charAt(i) == ')') { //遇到()就截取左右的字符串
                        if (i == len - 1) str = pollStr.substring(0, len - 1);
                        else {
                            str = pollStr.substring(0, i) + pollStr.substring(i + 1);
                        }
                        if (set.add(str)) queue.offer(str); //如果是第一次添加,就添加到里面来,这里是去重
                    }
                }
            }
            if (flag) break;          //如果本次长度的循环结束，而且，flag为true,就跳出循环，不必判断n-1的情况
        }
        if (res.size() == 0) res.add("");
        return res;
    }

    public boolean isValid(String s) {       //判断s是否满足括号匹配,注意s可能是含有字母的
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;
            else if (s.charAt(i) == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    /**
     * 题目02（leetcode 第42题）:接雨水
     * 描述:给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * <p>
     * <p>
     * 核心: 二边最大高度的最小值!!!!!
     * 思路01(对碰双指针法，一次遍历，个人认为是最优解): 左右各自维护一个leftMax,rightMax,i,j进行扫描，如果小的就计算雨水，而且指针移动
     * 思路02(动态规划): 左右分别动态规划,核心是求出最近比他高的，二边最大高度的最小值
     * 思路03(单调栈): 维护一个单调栈,过程比较复杂,width * high。
     */
    public int trap(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        int lMax = 0, rMax = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);     //对方比我大，才会动，而且最大高度一直都是在更新的
            if (height[l] < height[r]) {       //这是是谁小计算谁，而且移动(就可以出现比较大的值)
                res += lMax - height[l];
                l++;
            } else {
                res += rMax - height[r];
                r--;
            }
        }
        return res;
    }









}
