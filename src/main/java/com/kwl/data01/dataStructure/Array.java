package com.kwl.data01.dataStructure;

import java.util.Arrays;
import java.util.List;

/**
 * 数组的方法
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class Array {

    public static void main(String[] args) {
        //数组的定义、常见的api集合
        String[] strs = {"王二", "张三", "李四"};
        List<String> list = Arrays.asList(strs);  //数组转化为List类型
        Integer[] arrInt = {12, 1, 23};
        Arrays.sort(arrInt);    //数组排序会改变原来数组
    }
}
