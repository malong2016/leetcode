package com.kwl.data01;


import com.kwl.data01.bean.Person;
import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;


@SpringBootTest
class Data01ApplicationTests {





    @Test
    void contextLoads() {
        Stack<String> stack = new Stack<>();
        stack.push("张三");
        System.out.println(stack.pop() == "张三");
    }


}
