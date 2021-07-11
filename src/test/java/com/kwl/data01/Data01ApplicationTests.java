package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class Data01ApplicationTests {


    @Test
    void test() {
        double sk = 1.2323;
        int sk01 = (int) sk;  //精度大的转化为精度小的要强制类型转化
        //子转化为父要强制类型转化
        System.out.println("sk01 = " + sk01);
    }

}
