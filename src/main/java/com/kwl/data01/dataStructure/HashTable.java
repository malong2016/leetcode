package com.kwl.data01.dataStructure;


import java.util.Map;
import java.util.TreeMap;

/**
 * 哈希表(也叫散列表)
 * @author kuang.weilin
 * @date 2021/2/25
 */
public class HashTable {

    /**
     * 原理: java里面是使用HashMap处理哈希表
     * key-->哈希函数-->value对应的内存地址!!!找到value
     *
     * 插入: put(key,value) 如果存在key,会覆盖原来的value,而且返回原来key对应的value
     * 删除: remove(key) remove(key,value)
     * containsKey/Value("kye/value"): 是否包含key/value
     * size(): hashMap的长度
     * isEmpty(): hashMap是否为空
     */
    public static void main(String[] args) {
        //输入理解treeMap
        Map<String, String> map = new TreeMap<>();
        map.put("01", "张三");
        map.put("02", "李四");
        System.out.println(map.get("01"));
    }
}
