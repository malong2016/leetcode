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
    /**
     * 题目05(leetcode 第169题): 多数元素
     * 描述: 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 思路01:利用HashMap逐个统计,最后遍历
     * 思路02(最优解):摩尔投票法找出众数,设置一个x,扫描下一个,如果等于vote++,不等于vote--
     * vote变成0之后,换数
     */
    public int majorityElement(int[] nums) { //投票计数法
        int res = 0, vote = 0;
        for (int num : nums) {
            if (vote == 0) res = num;
            vote += num == res ? 1 : -1;   //是本元素是+1,不是就-1;
        }
        return res;
    }

    /**
     * 题目06（leetcode 第5题）: 最长回文子串
     * 描述: 给你一个字符串 s，找到 s 中最长的回文子串
     * 思路: 中心扩散
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String len1 = centreExpand(s, i, i);
            String len2 = centreExpand(s, i, i + 1);
            res = res.length() < len1.length() ? len1 : res;
            res = res.length() < len2.length() ? len2 : res;
        }
        return res;
    }

    public String centreExpand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    /**
     * 题目07（leetcode 第3题）:无重复字符的最长子串
     * 描述: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 思路: HashMap/Set/new int[128]
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            res = Math.max(res, i - left + 1);
            map.put(s.charAt(i), i);
        }
        return res;
    }

    public int lengthOfLongestSubstring01(String s) {       //new int[128]
        int[] arr = new int[128];
        int res = 0, l = 0;
        for(int r = 0; r < s.length(); r++){
            while(arr[s.charAt(r)] != 0){
                arr[s.charAt(l++)]--;      //这个是里面的char
            }
            res = Math.max(res,r - l + 1);
            arr[s.charAt(r)]++;
        }
        return res;
    }

    public int lengthOfLongestSubstring03(String s) {     //hashSet移除制定的
        if (s == null) return -1;
        Set<Character> hashSet = new HashSet<>();
        int left = 0,res = 0;
        for (int right = 0; right < s.length(); right++) {
            while(hashSet.contains(s.charAt(right))) hashSet.remove(s.charAt(left++));
            res = Math.max(res, right - left + 1);
            hashSet.add(s.charAt(right));
        }
        return res;
    }

    /**
     * 题目08（leetcode 第11题）:盛最多水的容器
     * 描述: 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 思路01(官方对碰双指针法): 和上题思路差不多
     */
    public int maxArea(int[] height) {  //双指针对碰法
        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            res = Math.max(res, Math.min(height[l], height[r]) * (r - l));    //更新结果
            if (height[l] < height[r]) l++;      //保留比较大的值!!!
            else r--;
        }
        return res;
    }





}
