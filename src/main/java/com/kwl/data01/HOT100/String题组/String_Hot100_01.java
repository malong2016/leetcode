package com.kwl.data01.HOT100.String题组;

import java.util.*;

/**
 * String题组-01
 *
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class String_Hot100_01 {


    /**
     * 题目01（leetcode 第20题）:有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 1)左括号必须用相同类型的右括号闭合。
     * 2)左括号必须以正确的顺序闭合。
     * <p>
     * 思路01(常规解法): 利用Stack
     * 思路02(巧解): 循环leng/2次，每次都是替换{}/[]/()到""
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(')');
            else if (s.charAt(i) == '{') stack.push('}');
            else if (s.charAt(i) == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != s.charAt(i)) return false;
        }
        return stack.isEmpty();
    }

    public boolean isValid01(String s) {        //巧解，循环length/2次，每次都是替换{}/[]/()到""
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("{}", "").replace("[]", "").replace("()", "");
        }
        return s.length() == 0;
    }


    /**
     * 题目02（leetcode 第394题）: 字符串解码
     * 描述: 给定一个经过编码的字符串，返回它解码后的字符串。
     * <p>
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * <p>
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * <p>
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"             注意: 输入的数字只是要表示要重复多少次，然后解码之后的输出全部是字母
     * 示例 2：
     * <p>
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     */
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();    //这里要设置为string,当有3*a会转化为string类型
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {        //先判断结束!!else就可以省略很多条件!!
                String temp = "";
                while (!stack.peek().equals("[")) {
                    temp = stack.pop() + temp;     //注意后出栈的要放入在前面，因为后面要入栈
                }
                stack.pop();  //将"["出栈
                String strNum = "";
                //拿到倍数,注意这里数字可以有多个!!所以要循环
                while (!stack.isEmpty() && stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9') {
                    strNum = stack.pop() + strNum;
                }
                int num = Integer.parseInt(strNum);
                String res = "";      //转化最后的String
                for (int j = 0; j < num; j++) res = res + temp;
                stack.push(res);         //将转化好的字符串重新入栈
            } else stack.push(String.valueOf(c)); //当时数字/字母/[直接入栈
        }
        String ans = "";
        while (!stack.isEmpty()) ans = stack.pop() + ans;       //将栈中所有元素出栈,先出的在后面
        return ans;
    }

    /**
     * 题目03（leetcode 第76题）:最小覆盖子串
     * 描述: 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * eg01:
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 输入：s = "a", t = "a"
     * 输出："a"
     * <p>
     * 思路01(官方解,见leetcode): 二个hashMap,设置滑动窗口,需要cheak检查窗口的s是否包括need的字符
     * 思路02(评论区高赞): 二个HashMap + vaild验证
     * 思路03(最优解): new int[128] + vaild验证 (这个是最快的)
     */
    public String minWindow(String s, String t) {        // new int[128]方法,最优解
        int[] need = new int[128], window = new int[128];
        int needSize = 0;
        for (int i = 0; i < t.length(); i++) {
            if (need[t.charAt(i)] == 0) needSize++;       //第一次统计就++,这里是统计不同的数
            need[t.charAt(i)]++;   //将t的元素统计
        }
        int l = 0, r = 0, valid = 0, resLen = Integer.MAX_VALUE;  //设置左右扫描指针
        String resStr = "";
        while (r < s.length()) {
            char c1 = s.charAt(r);
            if (need[c1] != 0) {
                window[c1]++;
                if (need[c1] == window[c1]) valid++;
            }
            while (valid == needSize) {
                if (r - l + 1 < resLen) {
                    resLen = r - l + 1; //更新长度
                    resStr = s.substring(l, r + 1);  //更新返回的长度
                }
                char c2 = s.charAt(l);
                if (need[c2] != 0) {
                    if (need[c2] == window[c2]) valid--;
                    window[c2]--;
                }
                l++;                //移动left,
            }
            r++;            //移动right
        }
        return resStr;
    }

    /**
     * 题目04(leetcode 第438题)  找到字符串中所有字母异位词
     * 描述: 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * 注意:
     *   字母异位词指字母相同，但排列不同的字符串。 （本题相同的是包括的）
     *   不考虑答案输出的顺序。
     * eg:
     *  输入:
     *     s: "cbaebabacd" p: "abc"
     * 输出:
     *    [0, 6]
     *
     * 思路01: 滑动窗口不动的超时！！！
     * 思路02: 双map+vaild比较,windows只放入need中存在的值!!!
     * 思路03: new int[128]单双窗口比较
     */
    public List<Integer> findAnagrams07(String s, String p) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        int l = 0, r = 0;     //定义窗口的初始值
        int valid = 0;              //window和need对应的验证，如果valid = need.length就匹配上了
        while (r < s.length()) {
            char c1 = s.charAt(r);
            if (need.containsKey(c1)) {        //如果need里面存在就加入到window中
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (need.get(c1).equals(window.get(c1))) valid++;    //如果加入的时候,符合就valid
            }
            if (r - l + 1 == p.length()) {       //注意:这里一移动就不会等于,所以可以用If代替while
                if (valid == need.size()) res.add(l);  //符合条件把起始index添加进来
                char c2 = s.charAt(l);
                if (need.containsKey(c2)) { //注意,need不一定包含c2,如果need包含那么windows一定是包含的
                    if(window.get(c2).equals(need.get(c2))) valid--;
                    window.put(c2, window.get(c2) - 1);
                }
                l++;
            }
            r++;
        }
        return res;
    }
    public List<Integer> findAnagrams08(String s, String p) {
        int[] window = new int[128];
        int[] need = new int[128];
        LinkedList<Integer> res = new LinkedList<>();
        int amount = 0;
        for (int i = 0; i < p.length(); i++) {
            if (need[p.charAt(i)] == 0) amount++;  //计算p里面有多少不同个值
            need[p.charAt(i)]++;
        }
        int l = 0, r = 0;
        int valid = 0;
        while (r < s.length()) {
            char c1 = s.charAt(r);
            if (need[c1] != 0) {
                window[c1]++;
                if (need[c1] == window[c1]) valid++;
            }
            if (r - l + 1 == p.length()) {
                if (valid == amount) res.add(l);
                char c2 = s.charAt(l);
                if (need[c2] != 0) {
                    if (need[c2] == window[c2]) valid--;
                    window[c2]--;
                }
                l++;
            }
            r++;
        }
        return res;
    }





}
