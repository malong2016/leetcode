package com.kwl.data01.bean;

import com.kwl.data01.dataStructure.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


import java.util.*;

/**
 * @author kuang.weilin  测试不pull可以不可以commit --> push
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
        //测试break在多重循环的表现
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            for (int j = 0; j < 4; j++) {
                if (i == 1 && j == 2) break;     //测试会不会结束大的循环!!!!~
            }
        }
    }
}
