package com.kwl.data01.swordOffer.String题组;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class StringSword_01 {


    /**
     * 题目01(swordOffer 第17题): 打印从1到最大的n位数
     * <p>
     * 思路01(基础解): 最大Math.pow(10,n)-1
     * 思路02(大数打印法,参考leetcode): todo
     */
    public int[] printToMaxOfNDigits(int n) {
        int max = (int) (Math.pow(10, n) - 1);
        int res[] = new int[max];          //注意是返回值,res[]不能有空隙
        for (int i = 0; i < max; i++) res[i] = i + 1;
        return res;
    }


    /**
     * 题目02(swordOffer 第05题): 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 例如: "we are happy" 输出为 "we%20are%20happy"
     */
    public String replaceBlank01(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
//        for (int i = 0; i < stringBuilder.length(); i++) {
//            if (stringBuilder.charAt(i) == ' ') {                     //遍历stringBuilder如果为' '就替换
//                stringBuilder.replace(i,i+1,"%20");
//            }
//        }
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i) == ' ' ? "%20" : str.charAt(i));
        }
        return stringBuilder.toString();
    }

    /**
     * 题目03(swordOffer 第48题): 最长不含重复字符的子字符串
     * 描述: 请从字符串中寻找一个最长的不包括重复字符的子字符串,计算该最长子字符的长度。
     * 假设字符串中只包含'a'~'z'的字符。
     * 方法一: HashMap
     * 方法二: HashSet
     * 方法三: 使用new int[128] A~Z 65 到90   a~z 97~122
     */
    public int lengthOfLongestSubString(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, res = 0;
        while (r < str.length()) {
            if (map.containsKey(str.charAt(r))) {       //注意是contains!
                l = Math.max(l, map.get(str.charAt(r)) + 1);
            }
            res = Math.max(res, r - l + 1);
            map.put(str.charAt(r), r);
            r++;
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {          //new int[128]解法
        int[] window = new int[128];
        int l = 0, r = 0, res = 0;
        while (r < s.length()) {
            while (window[s.charAt(r)] > 0) {       //大于0说明有重复的，此时还没有添加进来
                window[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);
            window[s.charAt(r++)]++;       //先添加，后面r++
        }
        return res;
    }

    /**
     * 题目04(swordOffer 第50题): 第一个只出现一次的字符
     * 描述: 字符串中寻找出第一个只出现的一次字符。如输入"abaccdeff",就输出"b"
     * <p>
     * <p>
     * <p>
     * 思路01(暴力解,时间复杂度是o(n2)):扫描字符串将每个字符和后面字符进行比较,如果后面字符没有就返回第一个这个字符
     * 思路02:设置一个HashMap(key,boolean) 不断的加入,boolean判断是否是已经加入,在扫描到第一个true
     * 思路03(假设是字母最大'z'是122):先将0-123全部设置为new int[123]然后扫描arr[char[i]]++进行计数,第一个arr[char[i]]==1就可以输出
     */
    public char firstUniqChar(String s) {
        //设置一个HashMap,key存放字符，value存放是否重复false,true;
        Map<Character, Boolean> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), !map.containsKey(s.charAt(i)));     //第一次来设置为true
        }
        for(int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i))){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public char firstNotRepeatingChar01(String str) {       //思路03
        int[] arr = new int[128];
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            arr[c]++;         //对应字符串转化为int类型进行计数,注意第一次数组为1的值,返回(char)index就是找到的值
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) return (char) i;        //、如果写int的变量要强转,如果直接写12就不需要转化
        }
        return ' ';     //如果不存在就返回null
    }


    /**
     * 题目05(swordOffer 第58题-II): 左旋转字符串
     * 描述:输入字符串"abcdefg"和数字2,该方法左旋转到cdefgab
     * <p>
     * 思路01(swordOffer官方解法): 把ab cdefg当成二个整体,先翻转ba gfedc 在分别整体翻转 cdefgab
     * 思路02: return s.substring(n) + s.substring(0,n);
     * 思路03: 使用StringBuild,先遍历后面n - s.length()，在遍历前面的0~n
     */
    public String leftRotateString(String str, int n) {
        if (str == null || str.length() < n || n < 0) return str;    //字符串为null,长度小于n,n小于0,不翻转,返回null
        char[] charArray = str.toCharArray();     //此时str不可能为null
        reverse(charArray, 0, n - 1);     //翻转前n个字符
        reverse(charArray, n, str.length() - 1);  //翻转其他字符
        reverse(charArray, 0, str.length() - 1); //翻转整体
        return String.valueOf(charArray);
    }

    /**
     * 题目01需要的方法,传入字符串和起始index和结束index,进行翻转
     */
    public void reverse(char[] charArray, int begin, int end) {       //翻转字符串,制定起始索引和结束索引
        while (begin < end) {
            char temp = charArray[end];
            charArray[end] = charArray[begin];
            charArray[begin] = temp;
            begin++;              //前p左移动,后p右移动
            end--;
        }
    }


    /**
     * 题目06(swordOffer 第58题-I): 翻转字符串(单词的顺序)
     * 描述: 输入一个英文句子,翻转句子中的单词的顺序,但是单词内字符串的顺序不变
     * "I am a student." ==> "student. a am I"
     * <p>
     * <p>
     * 思路01(二次翻转,swordOffer官方解法,不太好):先将字符串整体翻转,在每个单词翻转
     * 在翻转单词时候,pBegin和pEnd同时指向单词开始的地方,没有扫描到空格pEnd++,
     * 扫描到空格,pEnd--,翻转这个单词,pBegin=pEnd
     * 思路02(双指针从尾巴向前扫描)加入到StringBuild中来
     * 思路03: str.split(" ")先分割，然后在加入到StringBuild中来
     */
    public String reverseWords(String s) {
        //StringBuild用来接受,双指针从后向前扫描空格
        int l = s.length() - 1, r = s.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (l >= 0){
            while (l >= 0 && s.charAt(l) != ' ') l--;  //扫描到空格或者等于0就是一个完整的
            stringBuilder.append(s.subSequence(l + 1, r + 1)+" ");
            while(l >= 0 && s.charAt(l) == ' ') l--;      //去除空格,找到第一个不是空格
            r = l;
        }
        return stringBuilder.toString().trim();
    }
    public String reverseWords03(String s) {      //分割法
        //先分割
        String[] str =  s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i].equals("")) continue;
            else stringBuilder.append(str[i] + " ");
        }
        return stringBuilder.toString().trim();
    }
    /**
     * 题目07(leetcode 20) 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * <p>
     * 数值（按顺序）可以分成以下几个部分：
     * <p>
     * 若干空格
     * 一个 小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     * <p>
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     * <p>
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     */
    public boolean isNumber(String s) {
        return false;
    }


}
