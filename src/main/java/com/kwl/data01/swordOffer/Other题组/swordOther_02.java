package com.kwl.data01.swordOffer.Other题组;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他题组2
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class swordOther_02 {

    /**
     * 题目1(swordOffer 第62题): 圆圈中最后剩下的数字
     * 描述: 0,1,2,3...n-1者n个数字排成一个圆圈(注意核心是n-1最后会连接到0),从0开始,每次从
     * 这个圆圈删除第m个数字。求圆圈最后一个数字
     * <p>
     * 思路01(数学/动态规划):  每次删除
     * 思路02: 使用链表不断的进行删除
     */
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {        //最后一轮剩下2个人，从最后一人进行反推
            res = (res + m) % i;
        }
        return res;
    }

    public int lastRemaining01(int n, int m) {  //使用链表进行模拟
        int pos = 0;  //初始化位置，后面要用做记录每一轮开始的位置。
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(i);  //先把所有数加到动态数组里。
        while (list.size() > 1) { //循环条件要求list大小大于1，当只剩下最后一个数了就跳出
            int temp = (pos + m - 1) % list.size();
            list.remove(temp);
            pos = temp;
        }
        return list.get(0);
    }

    /**
     * 题目2(swordOffer 第16): 数值的整数次方!!! a^b
     * <p>
     * 思路01:直接求
     * 思路02: todo  块速幂求解
     */
    double power01(double base, int exponent) {
        double res = 1;
        for (int i = 0; i < exponent; i++) {
            res = res * base;       //遍历几次就base*base*base,几个base等价 base^exponent
        }
        return res;
    }

    /**
     * 题目2(swordOffer 第64题): 求1+2+...+n
     * 描述: 求1+2+3+....+n,要求不能使用乘除法,for,while,if,else,switch,case
     * 等关键字以及条件判断语句(A?B:C)
     * <p>
     * 思路: 利用&&进行短路终止判断
     */
    public  int res01 = 0;

    public  int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;  //当n=1的时候被短路
        res01 += n;
        return res01;
    }


    /**
     * 题目3(swordOffer 第65题): 不用加减乘除做加法
     * 描述: 写一个函数,求二个整数之和,要求在函数体内不可以使用'+','-','*','/'
     * <p>
     * 思路01: 利用位运算
     */
    public int add(int a, int b) {
        while (b != 0) {       //当进位为0的时候跳出
            int c = (a & b) << 1;       //c=进位
            a ^= b; //a=非进位和 (无进位和)
            b = c;
        }
        return a;
    }


}
