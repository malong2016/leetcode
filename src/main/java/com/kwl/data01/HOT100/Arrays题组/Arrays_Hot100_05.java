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

    /**
     * 题目03(leetcode 第339题):  除法求值
     * 描述:给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
     * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
     * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
     * <p>
     * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
     * 解释：
     * 条件：a / b = 2.0, b / c = 3.0
     * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/evaluate-division
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        return null;
    }

    /**
     * 题目04(leetcode 第253题): 会议室 II
     * 描述:给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
     * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     * <p>
     * 思路01:先对interval根据开始时间进行排序(先入队的肯定开始时间比你早)。 设置一个优先队列，这个优先队列总是保存，结束时间。
     * 如果待入队列元素大于队首，那么就可以共用一个会议室，对顶元素出队。无论是否共用，待入队的结束结束都是要入队的
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);     //是默认的是吗???如果是多维数组，如何比较?
        priorityQueue.offer(intervals[0][1]);    //把第一个结束时间入队
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= priorityQueue.peek()) priorityQueue.poll();//开始时间大于队首的结束时间，如果可以共用一个会议室，那么队首出队
            priorityQueue.offer(intervals[i][1]);     //无论是否共用,都是要将入队的结束时间
        }
        return priorityQueue.size();
    }

    /**
     * 题目05(leetcode 第621题): 任务调度器
     * 描述:给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
     * 并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的 最短时间 。
     * <p>
     * 思路: 输入：tasks = ["A","A","A","B","B","B"], n = 2
     * 输出：8
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     * <p>
     * 思路01(构造法): 最大频率数maxExec,res01 = (maxExce - 1) * (n + 1) + maxCount(最大频率的个数), A -> x -> x - > A -> x -> x ->A
     * 每(n-1)为一组,最后会空出maxCount,res可能比tasks.length小，所以 res =  Max(res01,tasks.length)
     */
    public int leastInterval(char[] tasks, int n) {     //hashMap
        Map<Character, Integer> map = new HashMap<>();
        int maxTime = Integer.MIN_VALUE;       //最大次数
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
            maxTime = Math.max(maxTime, map.get(task));        //拿到最大次数
        }
        int maxCount = 0;      //最大次数的个数
        for (Integer value : map.values()) {
            if (value == maxTime) ++maxCount;
        }
        return Math.max((maxTime - 1) * (n + 1) + maxCount, tasks.length);
    }
    public int leastInterval01(char[] tasks, int n) {        //new int[128]
        int[] ints = new int[128];
        for (char task : tasks) {
            ints[task]++;
        }
        Arrays.sort(ints);
        int count = 0;
        for (int i = 127; i >=0 && ints[i] == ints[127]  ; i--) {
            count++;
        }
        return Math.max((ints[127] - 1) * (n + 1) + count, tasks.length);
    }

    public static void main(String[] args) {
    }
}
