package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;


@SpringBootTest
class Data01ApplicationTests {

    @Test
    void test() {
        StringBuilder stringBuilder = new StringBuilder("woshi");
        stringBuilder.insert(0, "kwl");
        System.out.println("stringBuilder = " + stringBuilder);
    }

}
