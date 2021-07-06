package com.kwl.data01;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;


@SpringBootTest
class Data01ApplicationTests {


    @Test
    void test() {     //记住,a--是先取值后自增
        int a = 10;
        if (--a < 10) {
            System.out.println("a = " + a);
        }
    }


}
