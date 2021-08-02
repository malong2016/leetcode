package com.kwl.data01.HOT100.Arrays题组;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/5 16:07
 */
public class Arrays_Hot100_03 {

    /**
     * 题目01(leetcode 第374题): 前 K 个高频元素
     * 描述: 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * <p>
     * eg：
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * <p>
     * 思路01: 基于快速排序
     * 思路02: 基于堆排序
     */
    public int[] topKFrequent(int[] nums, int k) {
        return null;
    }

    /**
     * 题目02(leetcode 第374题): 合并区间
     * 描述: 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * eg：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 思路01:
     */
    public int[][] merge(int[][] intervals) {
        return null;
    }

    /**
     * 题目03(leetcode 第207题): 课程表
     * 描述: 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * eg：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *
     * 核心: 本题可约化为： 课程安排图是否是 有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
     *
     * 思路01(bfs):
     * 思路02(dfs)
     */
    /**
     * 题目04(leetcode 第406题): 根据身高重建队列
     * 描述:
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每
     * 个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
     * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     * <p>
     * eg:
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * <p>
     * 思路01(按照升序来，先按照身高，后按照人数):
     * <p>
     * 排序01: [7,1], [7,0], [6,1], [5,4], [5,2], [5,0], [4, 4]
     */
    public int[][] reconstructQueue(int[][] people) {

        if (0 == people.length || 0 == people[0].length)
            return new int[0][0];
        //按照身高降序 K升序排序
//        Arrays.sort(people, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
//            }
//        });
        Arrays.sort(people, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]));    //lambd表达式
        List<int[]> list = new ArrayList<>();
        //K值定义为 排在h前面且身高大于或等于h的人数
        //因为从身高降序开始插入，此时所有人身高都大于等于h
        //因此K值即为需要插入的位置
        for (int[] i : people) {         //矮的向前插入不影响!!!在插入的时候一定都是比他高的
            System.out.println("i = " + i[1]);
            list.add(i[1], i);
        }
        System.out.println("list = " + list);
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 题目05(leetcode 第31题): 下一个排列
     * 描述:
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     * <p>
     * 题目解读: 也就是找下一个这个数更大的值。
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     * <p>
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     * <p>
     * 思路01(维基百科):
     * 1) 先找出最大的索引 k 满足 nums[k] < nums[k+1]，如果不存在，就翻转整个数组；    --这个是从后向前找,如果i=-1，说明没有找到
     * 2) 再找出另一个最大索引 l 满足 nums[l] > nums[k]；                                             --从后向前走
     * 3) 交换 nums[l] 和 nums[k]；
     * 4) 最后翻转 nums[k+1:]。
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--; //这里跳出循环就是nums[k]<num[k+1]或者i=-1(也就是全部降序)
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) j--;     //跳出循环是nums[i] < num[j] ，这里是一定能找到的,因为前面有升序
            swap(nums, i, j);   //交换
        }
        reverseArr(nums, i + 1);      //交换
    }

    void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    void reverseArr(int[] nums, int beginIndex) {       //翻转
        int l = beginIndex, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    /**
     * 题目06(leetcode 第4题): 寻找两个正序数组的中位数
     * 描述: 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * eg:
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * <p>
     * 思路01: 先合并在调库排序，直接返回中位数
     * 思路02: 不需要真的合并，而是循环(len01 + len02) /2 + 1,设置二个指针p1和p2分别指向数组
     * 具体流程如下
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) { //调库
        int p1 = 0, p2 = 0, len = nums1.length + nums2.length;
        int left = -1, right = -1;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (p1 < nums1.length && (p2 >= nums2.length || nums1[p1] < nums2[p2])) { //p2越出，或者p1小于p2，移动小的
                right = nums1[p1++];
            } else right = nums2[p2++];
        }
        if ((len & 1) == 0) return (left + right) / 2.0;
        else return right;
    }


    /**
     * 题目07(leetcode 15题):三数之和
     * 描述: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * eg: nums = [-1,0,1,2,-1,-4] --> [[-1,-1,2],[-1,0,1]]
     * <p>
     * 思路01: 固定一端，左右扫描
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //先固定一端(遇到相同要去重)，在二端进行逼近(遇到相同要去重)，
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;           //因为这里已经是排好序的，大于0，直接返回
            if (i > 0 && nums[i] == nums[i - 1]) continue;   //去重
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;       //去重，移动到最后一个相同的值那么
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;           //去重之后要同时移动,因为这里的二个数都是添加了的
                    r--;
                } else if (sum < 0) l++;        //l右移动，扩大
                else r--;         //r左移动，缩小
            }
        }
        return res;
    }

    /**
     * 题目08(leetcode 第253题): 会议室 II
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


}
