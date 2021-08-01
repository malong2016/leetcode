package com.kwl.data01.HOT100.Arrays题组;

import com.kwl.data01.algorithm.ArraysSort;

import java.util.*;

/**
 * leetcode热题HOT100_数组_题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_02 {


    /**
     * 题目01(leetcode 896题): 如果数组是单调递增或单调递减的，那么它是单调的。
     * 描述: 前后可以等于!!! 可以顺序或者到顺
     * eg: [1,2,2,3]  --> true   [1,3,2] -- false
     * <p>
     * 思路01(一次遍历):先设置inc = true,des = true, 如果遇到arr[i]>arr[i+1] 就把inc设置为false,反之把des设置为false,
     * 最后inc||des,有一个为true就是true
     * 思路02(二次遍历): 同时验证是升序或者是降序,满足其一就可以return isSorted(A, true) || isSorted(A, false);
     */
    public boolean isMonotonic(int[] A) {
        if (A == null) return true;
        boolean inc = true, des = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) inc = false;
            if (A[i] < A[i + 1]) des = false;
        }
        return inc || des;
    }


    /**
     * 题目02(leetcode 第215题): 数组中的第K个最大元素
     * 描述: 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 思路01: 先排序Arrays.sort(nums);返回  return nums[nums.length-k];
     * 思路02: 快速排序和堆排序     todo 理解快速排序
     */

    public int findKthLargest014(int[] nums, int k) {      //堆排序
        //这里使用堆排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(); //最大就弄最小推，每次都是把最小值出队。里面就可以保持到最大值
        for(int num : nums){
            priorityQueue.offer(num);
            if(priorityQueue.size() > k) priorityQueue.poll(); //如果队中大于k，就把最小值出队，所以队中始终保持是k个元素
        }
        return priorityQueue.peek();
    }
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    int quickSort(int[] nums, int low, int high, int index) {     //要找元素的index
//        if (low < high) {           //这种标准循环就不行,必须使用逼近型，因为可能排好，就不用该index
//            int mid = findMid(nums, low, high);
//            if (mid == index) {
//                res = nums[mid];
//                System.out.println("找到了res = " + res);
//            }
//            quickSort(nums, low, mid - 1,index);
//            quickSort(nums, mid + 1, high,index);
//        }
        int mid = ArraysSort.partition(nums, low, high);    //这是我们自带的
        if (mid == index) {
            return nums[mid];
        } else {
            //mid大于就去小的地方搜索
            return mid > index ? quickSort(nums, low, mid - 1, index) : quickSort(nums, mid + 1, high, index);
        }
    }

    /**
     * 题目03(leetcode 第136题): 只出现一次的数字
     * 描述: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 思路01: res = 0,在遍历数组进行异或,相同会异或为0,最后剩下就是所求的值
     * 思路02: HashSet,如果不存在就添加，存在就移除，最后剩下的数的就是res
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /**
     * 题目04(leetcode 第238题): 除自身以外数组的乘积 -- 本题和swordOffer 66题一样
     */
    public int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length];    //除了本身之外的乘积
        pre[0] = 1;
        for(int i = 1; i < nums.length;i++){       //除了本身之后前置
            pre[i] = pre[i-1] * nums[i - 1];
        }
        int back = 1;
        for(int i = nums.length - 1;i >= 0; i--){
            pre[i] = back * pre[i];
            back = back * nums[i];
        }
        return pre;
    }

    /**
     * 题目05(leetcode 739题): 每日温度
     * 描述: 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
     * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替
     * <p>
     * eg:  输入: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 输出:  [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 思路01(暴力解): 双指针扫描
     * 思路02(单调栈，单调递减栈): 按照顺序扫描数组，注意是index入栈，入栈时候，比较入栈index对应的value,和栈顶大小，把比入栈value小的节点都
     * 出栈，记录结果，利用单调递减的特效保持单调栈
     */
    public int[] dailyTemperatures(int[] temperatures) {     //暴力解
        int n = temperatures.length;
        int[] res = new int[n];
        for (int i = 0; i < n - 1; i++) {   //最后一天不需要计算
            for (int j = i; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {     //找到了第一个大于i的直接加入res,结束本次循环
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures01(int[] temperatures) {       //单调栈
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer popIndex = stack.pop();   //要出栈的index
                res[popIndex] = i - popIndex;    //是哪个节点要你出栈的，之差就是结果
            }
            stack.push(i);  //无论如何都是要把i入队的
        }
        return res;
    }

    /**
     * 题目06(leetcode 第240题): 搜索二维矩阵 II              ---本题和 swordOffer 第4题 重复
     * 描述: 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * eg:
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     * <p>
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
     * 输出：false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;            //注意这里i和j越大，值越大。
        while(i >= 0 && j < n){
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] > target) i--;  //找更小的值
            else j++;     //找更大的值
        }
        return false;
    }

    /**
     * 题目07(leetcode 第621题): 任务调度器
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

    /**
     * 题目08(leetcode 第56题):  合并区间
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
