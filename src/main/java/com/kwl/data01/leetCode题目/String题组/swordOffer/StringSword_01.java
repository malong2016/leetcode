package com.kwl.data01.leetCode题目.String题组.swordOffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 剑指offer_字符串(String)题组01
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class StringSword_01 {

    /**
     * 题目1(swordOffer 面试题58题目1):翻转字符串(单词的顺序)
     * 描述: 输入一个英文句子,翻转句子中的单词的顺序,但是单词内字符串的顺序不变
     * "I am a student." ==> "student. a am I"
     * <p>
     * 思路01(二次翻转):先将字符串整体翻转,在每个单词翻转
     * 在翻转单词时候,pBegin和pEnd同时指向单词开始的地方,没有扫描到空格pEnd++,
     * 扫描到空格,pEnd--,翻转这个单词,pBegin=pEnd
     */
    public static String reverseSentence(String str) {

        char[] charArray = str.toCharArray();
        int pBegin = 0, pEnd = charArray.length - 1;
        reverse(charArray, pBegin, pEnd);    //整体翻转

        pBegin = pEnd = 0;      //初始化,开始从开始扫描

        while (pBegin != charArray.length) {
            if (charArray[pBegin] == ' ') {
                pBegin++;
                pEnd++;
            } else if (pEnd == charArray.length || charArray[pEnd] == ' ') {       //要扫描到字符数组溢出,先判断pEnd == charArray.length
                reverse(charArray, pBegin, --pEnd);      //pEnd要向前移动一位到非空格
                pBegin = ++pEnd;          //同时移动到空格
            } else {
                pEnd++;
            }
        }
        return String.valueOf(charArray);
    }

    /**
     * 题目1(swordOffer 面试题58题目2):左旋转字符串
     * 描述:输入字符串"abcdefg"和数字2,该方法左旋转到cdefgab
     * <p>
     * 思路: 把ab cdefg当成二个整体,先翻转ba gfedc 在分别整体翻转 cdefgab
     */
    public static String leftRotateString(String str, int n) {
        if (str == null || str.length() < n || n < 0) return str;    //字符串为null,长度小于n,n小于0,不翻转,返回null
        char[] charArray = str.toCharArray();     //此时str不可能为null
        reverse(charArray, 0, n - 1);     //翻转前n个字符
        reverse(charArray, n, str.length() - 1);  //翻转其他字符
        reverse(charArray, 0, str.length() - 1); //翻转整体

        return String.valueOf(charArray);
    }

    /**
     * 题目1需要的方法,传入字符串和起始index和结束index,进行翻转
     */
    public static void reverse(char[] charArray, int begin, int end) {       //翻转字符串,制定起始索引和结束索引
        int behind = begin, head = end;        //前后指针
        while (head > behind) {
            char temp = charArray[behind];
            charArray[behind] = charArray[head];
            charArray[head] = temp;
            behind++;              //前p左移动,后p右移动
            head--;
        }
    }

    /**
     * 题目2(swordOffer 面试题50题目1):字符串中第一个出现的一次字符
     * 描述: 字符串中寻找出第一个只出现的一次字符。如输入"abaccdeff",就输出"b"
     * <p>
     * 思路01(暴力解,时间复杂度是o(n2)):扫描字符串将每个字符和后面字符进行比较,如果后面字符没有就返回第一个这个字符
     * 思路02:设置一个HashMap(key,boolean) 不断的加入,boolean判断是否是已经加入,在扫描到第一个true
     * 思路03(假设是字母最大'z'是122):先将0-123全部设置为new int[123]然后扫描arr[char[i]]++进行计数,第一个arr[char[i]]==1就可以输出
     */
    public static char firstNotRepeatingChar(String str) {       //思路02
        HashMap<Character, Boolean> hashMap = new HashMap<>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            hashMap.put(c, !hashMap.containsKey(c));  //第一次放入hashMap,一定返回true,第二次放入就是重置到false(表明有重复)
        }
        for (char c : charArray) {
            if (hashMap.get(c)) return c;
        }
        return ' ';     //如果不存在就返回null
    }

    public static char firstNotRepeatingChar01(String str) {       //思路03
        int[] arr = new int[123];
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            arr[c]++;         //对应字符串转化为int类型进行计数,注意第一次数组为1的值,返回(char)index就是找到的值
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) return (char) i;        //要强制转化,如果写int的变量,如果直接写int字面量就不需要转化
        }
        return ' ';     //如果不存在就返回null
    }

    /**
     * todo: 题目3(swordOffer 面试题38):字符串的排序
     * 描述: 输入一个字符串,打印出该字符串中字符的所有排序。例如，输入字符串abc，就打印出由
     * 字符a,b,c所能排列出来的所有字符串abc,acb,bca,cab,cba
     */

    /**
     * 题目4(swordOffer 面试题48):最长不含重复字符的子字符串
     * 描述: 请从字符串中寻找一个最长的不包括重复字符的子字符串,计算该最长子字符的长度。
     * 假设字符串中只包含'a'~'z'的字符。
     * eg: "arabcacfr"中最长不含有重复字符串是"acfr",长度是4
     * 思路01(暴力解,时间复杂度是o(n3)):先找到长度为n的字符串包括o(n2)的子字符串,在判断一个字符串是否包括重复字符串
     * 思路02(swordOffer 动态规划,麻烦,不好理解!!!!):f(i)表示第i个字符为结尾的时候不含重复元素的子字符串的最长长度.计算f(i)我们已经找到了f(i-1)
     * 如果第i个字符之前没有出现过,就f(i)=f(i-1)+1
     * 思路03(leetcode 双指针法,最优解): 指针i是从0到n进行扫描,指针rk用来维护上一个数组的结束位置.用HashSet用来判断是否存在
     * i右移动就从HashSet移除对应的元素,rk右移动,先判断是否存在,如果不存在就加入
     */
    public static int lengthOfLongestSubString(String str) {
        HashSet<Character> hashSet = new HashSet<>();
        int maxLength = 0, rk = -1;    //rk是字符串不重复的最后指针
        for (int i = 0; i < str.length(); i++) {      //指针i,遍历指针
            if (i != 0) hashSet.remove(str.charAt(i));     //指针向有移动就移除该元素
            while (rk+1 < str.length() && !hashSet.contains(str.charAt(rk+1))) {     //rk+1等于从上一级的末尾
                hashSet.add(str.charAt(rk + 1));
                rk++;
            }
            maxLength = Math.max(maxLength, rk - i + 1);//rk - i + 1是本字符开始的最大长度
        }
        return maxLength;
    }


    public static void main(String[] args) {
//        System.out.println("题目1(swordOffer 面试题58):翻转字符串(单词的顺序):");
//        String str = "I am a student.";
//        System.out.println(reverseSentence(str));
//        System.out.println("题目1(swordOffer 面试题58题目2):左旋转字符串:");
//        System.out.println(leftRotateString("abcdefg",2));

//        System.out.println("题目1(swordOffer 面试题50题目1):字符串中第一个出现的一次字符");
//        System.out.println(firstNotRepeatingChar01("abaccdeff"));

        System.out.println("题目4(swordOffer 面试题48):最长不含重复字符的子字符串");
        System.out.println(lengthOfLongestSubString("arabcacfr"));
    }


}
