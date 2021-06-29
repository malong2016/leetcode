package com.kwl.data01.myTest;


//import com.sun.org.apache.xpath.internal.operations.String;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/6/26 2:17
 */
public class MyTest02 {


//    reverse(chars, begin, --end);
//    reverse(chars, begin, end--);区别

   static void   fun01(int a){
        System.out.println("a = " + a);
    }



    public static void main(String[] args) {
       int a = 100;
       int b = 2;
        int arr[] = {99, 98, 100};
        System.out.println(arr[--b]==98);
        System.out.println(arr[b--]==98);
    }

}
