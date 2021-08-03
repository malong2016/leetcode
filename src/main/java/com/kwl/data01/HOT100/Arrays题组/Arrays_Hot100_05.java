package com.kwl.data01.HOT100.Arrays题组;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/9 22:10
 */
public class Arrays_Hot100_05 {

    /**
     * 题目01(leetcode 第347题):  前 K 个高频元素
     * 描述: 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * <p>
     * 思路: 先统计各元素出现的次数，然后加入优先队列中，优先队列是通过次数进行排序，然后依次出队，每次获取堆顶的元素
     * 就是出现次数最多的元素。出队k次，得到res.
     * 思路01: 使用堆排序，暂时使用的是内置的优先队列,模拟大顶堆(官方是模拟小顶堆，不断控制堆中元素是k)
     * 思路02: todo  快速排序
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);  //统计次数
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2[1] - o1[1]));
        for (Integer key : map.keySet()) {
            priorityQueue.offer(new int[]{key, map.get(key)});     //进队
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = priorityQueue.poll()[0];    //前k大的直接出队
        return res;
    }


}
