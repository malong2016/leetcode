package com.kwl.data01.dataStructure.题目Struct;

/**
 * @author kuang.weilin
 * @date 2021/7/10 15:50
 */

import java.util.*;

/**
 * leetcode 第146题: LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * eg:
 * ["LRUCache","get","put","get","put","put","get","get"]
 * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
 * <p>
 * 结果是: [null,-1,null,-1,null,null,2,6]
 * 思路01: LinkedHashMap
 * 思路02: hashMap + 双向链表      注意如果单纯的保存key,使用系统的双向链表，是超时的，可以手写双向链表
 */
public class LRUCache {


    int capacity;
    LinkedHashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        refresh(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            refresh(key);
        } else {
            if (map.size() == this.capacity) {
                int oldestKey = map.keySet().iterator().next();
                map.remove(oldestKey);
            }
            map.put(key, value);
        }
    }

    public void refresh(int key) {
        int val = map.get(key);
        map.remove(key);
        map.put(key, val);
    }
}
