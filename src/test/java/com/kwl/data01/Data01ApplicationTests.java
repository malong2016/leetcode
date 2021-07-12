package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class Data01ApplicationTests {


    int fun01(){
        int a = 100;
        return a = 103;
    }

    @Test
    void test() {
        System.out.println(fun01());
    }

}
