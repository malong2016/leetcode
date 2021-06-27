package com.kwl.data01.bean;


import com.kwl.data01.dataStructure.ListNode;
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

    private String city;

  static   List<List<Integer>> res = new ArrayList<>();

    /**
     * 三数之和大体思路
     * 先排序,在左右3指针进行扫描，注意中间要去重
     */
    public static void main(String[] args) {
    }
}
