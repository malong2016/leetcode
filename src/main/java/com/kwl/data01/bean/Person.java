package com.kwl.data01.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/2/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String id;

    private String name;

    private int city;

    private Student student;

    public static void main(String[] args) {
        int a = 12, b = 14;int c = 1;
        //循环体里面套ifcontinue能否跳出本循环??

    }

}
