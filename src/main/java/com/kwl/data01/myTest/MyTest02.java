package com.kwl.data01.myTest;


//import com.sun.org.apache.xpath.internal.operations.String;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/6/26 2:17
 */
public class MyTest02 {


    //广发 输入2进制转化为16进制
    public String toHexValue (int num) {
        long a = fac(num);
        String res = "";
        res = Long.toHexString(a);
        return res;
    }
    public long fac(int n){     //先求出n!
        if (n == 0) return 1;
        else return n * fac(n - 1);
    }

    public static void main(String[] args) {
        MyTest02 myTest02 = new MyTest02();
        System.out.println(myTest02.toHexValue(4));
        System.out.println(myTest02.toHexValue(10));

    }

    //广发输入json进行个人信息的脱敏计算
    public String maskJsonMsg (String jsonMsg) {

        Map<String, String> map = new HashMap<>();
        String[] str = jsonMsg.split(",");
        for (String str01 : str){
            String[] str02 = str01.split(":");
            map.put(str02[0],str02[1]);
        }
        return null;
    }




}
