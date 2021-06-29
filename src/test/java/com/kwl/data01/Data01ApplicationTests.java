package com.kwl.data01;


import com.kwl.data01.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@SpringBootTest
class Data01ApplicationTests {




    int fun01(){
        Stack<Integer> stack = new Stack<>();
        stack.push(122);
        return stack.pop();
    }


    @Test
    void contextLoads() {
        int a  = 15;
        System.out.println(a ^ a);  //0
        System.out.println(a & a);  //15
        System.out.println(a<<1); //30
        System.out.println(a>>1); //7
    }


}
