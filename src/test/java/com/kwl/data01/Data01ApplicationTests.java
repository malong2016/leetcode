package com.kwl.data01;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Data01ApplicationTests {


    public static String longestCommonPrefix(String[] strs) {
        //大体思路,res= strs[0],然后依次和后面的item比较,进行截取
        if (strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;      //j是扫描指针,同时扫描对比
            while (j < Math.min(res.length(), strs[i].length()) && res.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            res = res.substring(0, j);
            System.out.println("res = " + res);
            if (res == "") return "";
        }
        return "";
    }


    @Test
    void contextLoads() {
        int a = 1;

    }


}
