package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class Data01ApplicationTests {




    @Test
    void test() {
        //对于长度为0来讲
        int[][] arr = new int[0][3];
        System.out.println(arr[0]);
    }
}
