package com.kwl.data01.leetCode题目.String题组;

import java.util.Arrays;
import java.util.Stack;

/**
 * String题组-01
 *
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class String_01 {


    /**
     * 题目01:有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 1)左括号必须用相同类型的右括号闭合。
     * 2)左括号必须以正确的顺序闭合。
     * 思路01: 利用栈的思想,让字符串(转为为字符串数组)从左向右的字符不断的入栈,左括号就入栈对应右括号,右括号就和栈顶元素比较,
     * 如果匹配-->栈顶元素出栈,不匹配-->return false,字符串数组遍历完成之后,如果最后是栈空,就完成括号匹配,返回true,否则返回false
     */
    public boolean isValid01(String s) {
        if (s == null || s.length() %2!=0) return false;    //数组为null或者长度为奇数,不满足括号匹配
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {         //如果左括号是入栈对应的右括号
                stack.push(')');
            }else if (s.charAt(i) == '{'){
                stack.push('}');
            }else if (s.charAt(i) == '['){
                stack.push(']');
            } else if (stack.isEmpty() || s.charAt(i)!=stack.pop()) {          //如果是右括号,先判断栈是否空,如果为空或者栈顶元素匹配不上,return false
                return false;
            }
        }
        return stack.isEmpty();      //如果栈是空,return true,否则返回 fasle
    }

    /**
     *题目01:有效的括号
     *思路02:不断去替换[] {} (),最后替换完成之后为"",return true,否则 return false
     */
    public boolean isValid02(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {    //10/2 --> 5次  9/2 -->遍历4次  ()
          s =  s.replace("{}", "").replace("[]", "").replace("()", "");
        }
        return s.length()==0;
    }

    /**
     *利用StringBuilder默认入栈和出栈,执行速度快
     */
    public boolean isValid03(String s) {
        if (s == null || s.length() %2!=0) return false;    //数组为null或者长度为奇数,不满足括号匹配
        StringBuilder stringBuilder = new StringBuilder();  //当成一个栈来处理
        for (char c : s.toCharArray()) {       //遍历一个数组
            if (c == '(') {
                stringBuilder.append(')');
            } else if (c == '[') {
                stringBuilder.append(']');
            } else if (c == '{') {
                stringBuilder.append('}');
            } else if (stringBuilder.length() == 0 || stringBuilder.charAt(stringBuilder.length() - 1) != c) {
                return false;
            }else {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        return stringBuilder.length()==0;
    }

    /**
     * 题目02:请实现一个函数,把字符串中每个空格都替换成%20
     * 例如: "we are happy" 输出为 "we%20are%20happy"
     */
    /**
     * 02-01 思路：使用stringBuilder去做
     */
    public static String replaceBlank01(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == ' ') {                     //遍历stringBuilder如果为' '就替换
                stringBuilder.replace(i,i+1,"%20");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 02-02 思路: 使用传统的字符串,每次扫描到空格,把字符串右移动2个空格
     */
    public static String replaceBlank02(String str){
        
        return null;
    }

    /**
     * 02-03 思路: 先计算出空格,延长字符串str到返回字符串的长度,设置二个指针p1 p2,扫描,将前面的数据依次注入到后面形成新的字符串
     *
     * %20
     */
    public static String replaceBlank03(String str){

        int n = 0;                              //n代表空格的数量
        for (char c : str.toCharArray()) {
            if (c==' ') ++n;
        }
        int length = str.length()+n*2;
        char[] newCharArr = Arrays.copyOf(str.toCharArray(), length);    //这是返回字符数组
        int p2 = str.length()-1,p1 = newCharArr.length-1;        //p2指向老字符数组的尾巴,p1指向新字符串数组的尾巴
        while (p2 != -1) {        //全部扫描??p1!=p2
            if (newCharArr[p2] == ' ') {     //如果扫描到空格,赋值'0' '2' '%' ???
                newCharArr[p1--] = '0';
                newCharArr[p1--] = '2';
                newCharArr[p1--] = '%';
            }else {                           //扫描到非空格
                newCharArr[p1--] = newCharArr[p2];
            }
            p2--;                   //p2左移动
        }
        return String.valueOf(newCharArr);         //返回新的字符串
    }
    

    public static void main(String[] args) {
//        System.out.println(String_01.replaceBlank01("we  are happy"));
        System.out.println(String_01.replaceBlank03("we are happy"));
    }
}
