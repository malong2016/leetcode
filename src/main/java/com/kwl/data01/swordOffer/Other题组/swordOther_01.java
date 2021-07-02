package com.kwl.data01.swordOffer.Other题组;

import java.util.Arrays;

/**
 * 其他题组1
 *
 * @author kuang.weilin
 * @date 2021/2/18
 */
public class swordOther_01 {


    /**
     * 题目1(swordOffer 面试题15): 二进制中1的个数
     * 描述: 请实现一个函数,输入一个整数,输出该数二进制表示中1的个数
     * 例如: 把9表示成二进制1001,有2位是1.因此，如果输入是9，该函数输出是2
     * <p>
     * 思路01(swordOffer): 设置一个1不断左移动和1,10,100和传入的n进行比较
     * 思路02: (n-1)&n可以吧最右边的1变成0
     */
    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;           //每次进行n-1&n都是把最右边的1变成0
        }
        return count;
    }

    public static int numberOf2(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & n) != 0) count++;      //注意比较运算符优先级大于&位运算符!!!
            flag = flag << 1;
        }
        return count;
    }


    /**
     * 题目3(swordOffer 面试题43):1~n整数中1出现的次数
     * 描述: 输入一个整数n,1~n这个n整数的十进制中1出现的次数
     * eg: 输入12,那么1~12这些整数中包含1,10,11和12一共出现了5次
     * <p>
     * 思路01(swordOffer 暴力解): n%10==1,个位存在1,如果大于10,n/=10在进行判断
     */
    public static int numberOf1_my(int n) {
        int number = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i; //不能改变本身
            while (temp != 0) {
                if (temp % 10 == 1) number++;
                temp /= 10;
            }
        }
        return number;
    }
}
