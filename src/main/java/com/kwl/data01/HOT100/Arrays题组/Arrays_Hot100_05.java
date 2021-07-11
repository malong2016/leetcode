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

    /**
     * 题目02(leetcode 第56题):  合并区间
     * 描述: 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * eg:
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 思路01(官方思路): 先按照左端点进行升序排序。然后，
     * 1 如果待加入的元素左端值，大于拍好序的最大值，直接加入
     * 2 如果待加入元素左端值小于或者等于等于拍好序的最大值，那么就融合，左端值还是原来的左端值(因为一定更小)，右端值二者右端的最大值
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;       //传入个数0或者1直接返回本身
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));   //按照左端元素升序(注意排序默认就是升序)
        LinkedList<int[]> res = new LinkedList<int[]>() {{  //先把第一个元素添加进来
            add(intervals[0]);
        }};
        for (int i = 1; i < intervals.length; i++) {
            int[] resLast = res.getLast();     //拿到返回值的最后一个元素
            if (resLast[1] < intervals[i][0]) res.add(intervals[i]);   //最小值大于拍好序的最大值，直接加入
            else {
                resLast[1] = Math.max(resLast[1], intervals[i][1]); //右端取二者的最大值,注意,左端还是原来的
            }
        }
        return res.toArray(new int[0][]);    //转化为int[][]
    }






}
