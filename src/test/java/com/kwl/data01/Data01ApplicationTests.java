package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class Data01ApplicationTests {


    public int numberOf2(int n) {
        int flag = 1, res = 0;
        while (flag != 0) {         //这个可以循环32次
            if ((flag & n) != 0) res++;      //注意比较运算符优先级大于&位运算符!!!
            flag <<= 1;
        }
        return res;
    }


    @Test
    void test() {
        char a = '\0';
        System.out.println((int)a);
        char b = ' ';
        System.out.println((int)b);
    }
}
