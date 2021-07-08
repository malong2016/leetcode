package com.kwl.data01.algorithm;

/**
 * 深入理解 递归(Recursion)
 *
 * @author kuang.weilin
 * @date 2021/2/9
 */
public class Recursion {


    /**
     * 递归定义: 函数直接或者间接调用自己
     * <p>
     * 4个要素: 1)接受的参数 2)返回值 3)终止的条件 4)递归的拆解:如何递归下一层
     * 递归栈: 先入后出 eg:f(5) 先求f(4) f(3) f(2)这种
     * 空间复杂度: o(n)
     * 练习题: leetcode 509,206,344
     */
    public static int jiechen(int n) throws Exception {
        if (n < 0) throw new Exception("负数不存在阶乘,请重新输入!");
        if (n == 1 || n == 0) return 1;    //递归终止的条件
        return n * jiechen(n - 1);         //递归的拆解和返回值
    }

}
