package com.kwl.data01.HOT100.Arrays题组;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/7 0:17
 */
public class Arrays_Hot100_04 {

    /**
     * 题目01(leetcode 第239题): 滑动窗口最大值                  --本题和swordOffer 面试题59 重复
     * 描述: 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回滑动窗口中的最大值
     * 思路: 单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 1 - k, j = 0; j < nums.length; i++, j++) {
            if (i > 0 && nums[i - 1] == linkedList.peekFirst()) linkedList.pollFirst();  //可能前面的值已经被清除掉了
            while (!linkedList.isEmpty() && linkedList.peekLast() < nums[j]) linkedList.pollLast();
            linkedList.addLast(nums[j]);
            if (i >= 0) res[i] = linkedList.peekFirst();
        }
        return res;
    }

    /**
     * 题目02(leetcode 第581题): 最短无序连续子数组
     * 描述: 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     * eg:
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：0
     * <p>
     * 思路01(最优解): 先克隆数组，把数组排好序，然后遍历原数组，使用l和r指针维护，错序的最小和最大距离
     * 注意: 如果有乱序，那么left和right一定是不相等的
     * 思路02(单调栈): 具体看官方最优解
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] cloneNums = nums.clone();
        Arrays.sort(cloneNums);
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;      //l是维护最小距离,r是维护最大距离
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != cloneNums[i]) {        //夹在l和r中间的都是没有排好序的元素
                left = Math.min(left, i);
                right = Math.max(right, i);
//                right = i;        //这里i一定是比right更大的  -- 优化
            }
        }
        return left > right ? 0 : right - left + 1; //如果left和right没有动,那么原数组就是升序的,不需要维护
    }

    /**
     * 题目03(leetcode 第128题): 最长连续序列
     * 描述: 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 进阶: 要求时间复杂度是o(n),那么就是不能使用排序，用空间去换时间
     * <p>
     * 思路01: 使用HashSet先存储，后判断
     * 思路02: 先排序，后使用dp!!! 注意可能出现重复的数      dp[n]或者pre
     */
    public int longestConsecutive(int[] nums) {       //使用hashSet
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);          //添加进来
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            int count = 0;         //每次都是重新统计
            if (set.contains(num - 1)) continue;      //如果有比这个数小的，那么就不用计算,因为小的可能是比这个数的res大!
            while (set.contains(num++)) count++;           //统计个数
            res = Math.max(res, count);
        }
        return res;
    }

    public int longestConsecutive01(int[] nums) {       //动态规划pre!!
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 1, pre = 1;    //默认就是nums[0]
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue; //pre不需要更新
            else if (nums[i] == nums[i - 1] + 1) res = Math.max(res, ++pre);  //pre先自增1，在比较
            else pre = 1;            //不是联系,就直接设置为1
        }
        return res;
    }

    /**
     * 题目04(leetcode 第300题): 最长递增子序列
     * 描述: 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 思路(动态规划): 双循环,先固定右端j,然后从i从0开始遍历到j-1,如果dp[j] > dp[i]就在对应的dp[i]+1
     * dp[]初始值都是要设置为1，双循环不断的更新dp
     */
    public int lengthOfLIS(int[] nums) {    //从o开始
        int[] dp = new int[nums.length];
        int res = Integer.MIN_VALUE;
        Arrays.fill(dp, 1);       //初始值全部赋值为1
        for (int j = 0; j < nums.length; j++) {                //j是固定的右端,如果每次都是统计dp[j]的值,只要是前面的值都可以
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) dp[j] = Math.max(dp[i] + 1, dp[j]);        //这里是dp[j]符合升序才要换!!
            }
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    public int lengthOfLIS01(int[] nums) {       //从1开始
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) dp[j] = Math.max(dp[i] + 1, dp[j]);
            }
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    /**
     * 题目05(leetcode 第49题): 字母异位词分组
     * 描述: 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * <p>
     * 思路01(HahsMap + 排序法): 在HashMap设置key = "排好序的字符",value = List<List<String>>
     * 如果你第一次来,那么就就put('item',new List<List<String>>),或者就把这个元素添加进去
     * <p>
     * 思路02: todo 字母异位词分组还有更好的方法???
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charStr = str.toCharArray();
            Arrays.sort(charStr);
            String strSort = String.valueOf(charStr);     //拿到排好序的str
//            List<String> list = map.getOrDefault(strSort, new LinkedList<String>());     //拿到映射表中的value,如果映射表中没有就创建
//            list.add(str);
//            map.put(strSort, list);          //添加进来
            if (!map.containsKey(strSort)) map.put(strSort, new LinkedList<String>());   //先添加/最优解
            map.get(strSort).add(str);        //后拿到加入
        }
        return new LinkedList<>(map.values());
    }



    /**
     * 题目06(leetcode 第287题): 寻找重复数
     * 描述:
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * 思路01:
     */
    public int findDuplicate(int[] nums) {
        return 0;
    }

    /**
     * 题目07(leetcode 第48题):  旋转图像
     * 描述:
     *  给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *  你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     *
     * 思路01: 先水平翻转,在主对角线翻转
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m / 2; i++) {           //水平翻转,横坐标在变，纵坐标不变
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m - 1 - i][j];
                matrix[m - 1 - i][j] = temp;
            }
        }
        for (int i = 0; i < m; i++) {        //主对角线翻转,注意我们起手的是左下方
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 题目08(leetcode 第75题): 颜色分类
     * 描述: 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     *
     * 思路01: 单指针+二遍排序 ,一趟遇到0就交换到前面来,二趟遇到1就交换到前面来。指针p前面都是排好的数据
     * 思路02: 双指针+ 一遍排序
     */
    public void sortColors(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0) swap(nums, p++, i);   //交换完成之后p++
        }
        for (int i = p; i < nums.length; i++){
            if (nums[i] == 1) swap(nums, p++, i);
        }
    }
    void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
    public void sortColors01(int[] nums) {         //双指针,不好理解
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {            //这里是p0把p1的'1'交换到i处了，所以要交换回来
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }


}
