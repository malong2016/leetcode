package com.kwl.data01.bean;

import com.kwl.data01.dataStructure.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


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

    private String city;

    public static void main(String[] args) {

    }
}
