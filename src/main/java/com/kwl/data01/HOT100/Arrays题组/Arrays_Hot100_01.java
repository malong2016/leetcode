package com.kwl.data01.HOT100.Arrays题组;

import java.util.*;

/**
 * leetcode热题HOT100_数组_题组01(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class Arrays_Hot100_01 {

    /**
     * 题目01(leetcode 第1题): 两数之和
     * 描述: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值的那两个整数,并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。你可以按任意顺序返回答案
     * eg: 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
     * <p>
     * 思路01: 暴力解
     * 思路02: 将nums[i]和i写入到HashMap之中,牺牲空间换时间
     */
    public int[] twoSum(int[] nums, int target) {  //先把i,nums[i]加入到map中,进行判断
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {     //这里是符合条件的i和j
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 题目02(leetcode 第4题):寻找二个正序数组的中位数,如果中位数是二个,就取平均
     * eg: nums1 = [1,2], nums2 = [3,4]  --> (2+3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) { //调库
        int p1 = 0, p2 = 0, len = nums1.length + nums2.length;
        int left = -1, right = -1;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (p1 < nums1.length && (p2 >= nums2.length || nums1[p1] < nums2[p2])) { //p2越出，或者p1小于p2，移动小的
                right = nums1[p1++];
            } else {
                right = nums2[p2++];
            }
        }
        if ((len & 1) == 0) return (left + right) / 2.0;
        else return right;
    }


    /**
     * 题目03(leetcode 第33题): 搜索旋转排序数组
     * 描述: 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * <p>
     * eg: nums = [4,5,6,7,0,1,2], target = 0  --> 4
     * <p>
     * 思路:如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，
     * 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
     */
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[high]) {       //上半区是有序的
                if (nums[mid] < target && target <= nums[high]) {      //在本有序区间
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {                   //下半区是有序的
                if (nums[low] <= target && target < nums[mid]) {   //在本有序区间
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return low;
    }

    /**
     * 题目04(leetcode 34题): 在排序数组中查找元素的第一个和最后一个位置  (类似swordOffer 面试题53)
     * 描述: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶: 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * eg: nums = [5,7,7,8,8,10], target = 8 --> [3,4]
     * <p>
     * 思路01: 顺序查找,时间复杂度是o(n)
     * 思路02: 二分查找
     */
    public int[] searchRange(int[] nums, int target) {
        //二次循环，查找二次的index，一次l，一次r
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;     //注意nums为0的情况
        int l = 0, r = nums.length - 1;
        while (l < r) {         //这里和二分查找不一样
            int mid = (l + r) / 2;
            //目标值小，r向下靠
            if (nums[mid] >= target) r = mid;     //r不断向左靠近mid,而且在mid等l
            else l = mid + 1;
        }
        if (nums[l] != target) return res;      //没有找到直接返回res
        res[0] = l;
        r = nums.length;     //可以缩小范围,前面已经找到第一个index.注意l最后的落值可能是len,最后拿到的长度是l-1
        while (l < r) {
            int mid = (l + r) / 2;
            //目标值大，l向上靠
            if (nums[mid] <= target) l = mid + 1;      //l在比mid+1的地方等r,向左靠近目标值
            else r = mid;
        }
        res[1] = l - 1;
        return res;
    }

//    public int[] search01(int[] nums, int target) {
//        int[] res = new int[]{-1, -1};
//        if (nums.length == 0) return res;     //注意nums为0的情况
//        // 搜索右边界 right
//        int i = 0, j = nums.length - 1;
//        while (i <= j) {
//            int m = (i + j) / 2;
//            if (nums[m] <= target) i = m + 1;
//            else j = m - 1;
//        }
//        int right = i;
//        // 若数组中无 target ，则提前返回
//        if (j >= 0 && nums[j] != target) return res;
//        // 搜索左边界 right
//        i = 0;
////        j = nums.length - 1;
//        j = right;        //是否可以
//        while (i <= j) {
//            int m = (i + j) / 2;
//            if (nums[m] < target) i = m + 1;
//            else j = m - 1;
//        }
//        int left = j;
//        return new int[]{left, right};
//    }


    /**
     * 题目05(leetcode 第560题): 和为k的子数组
     * 描述: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * eg: 输入: nums = [1,1,1], k = 2  --> 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * 思路01（暴力解）: 双指针扫描
     * 思路02(前缀和): 使用HashMap对每个index的前缀和次数进行统计，当遍历到当前节点其前缀和是preSum,
     * 在HashMap查找preSum - k的数量, 注意preSum - preSum + k得到二者相减就是中间k,也就是连续子数组。
     * 注意在初始化的时候，我们要把HashMap.put(0,1),前缀和为0是1次
     */
    public int subarraySum01(int[] nums, int k) {      //前缀和
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];                        //统计当前index的前缀和
            if (map.containsKey(preSum - k)) {
                res += map.get(preSum - k);       //拿到前缀和是preSum-k的个数,preSum - (preSum - k)就是k
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);     //把当前index的前缀和添加进来
        }
        return res;
    }

    public int subarraySum(int[] nums, int k) {      //暴力解
        int res = 0;
        for (int i = 0; i < nums.length; i++) {   //固定一端，向后走
            int temp = 0;                  //要更新
            for (int j = i; j < nums.length; j++) { //从固定的这一端开始
                temp = temp + nums[j];
                if (temp == k) res++;
            }
        }
        return res;
    }


    /**
     * 题目06(leetcode 283题): 移动零
     * 描述: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * eg:输入: [0,1,0,3,12]  --> 输出: [1,3,12,0,0]
     * 说明:  1必须在原数组上操作，不能拷贝额外的数组。
     * 2尽量减少操作次数。
     * <p>
     * 思路01: 双指针,把非0换到前面,注意，如果交换把0换到后面，会改变数组的顺序
     * 思路02: 遇到非0就注入到前面，最后用0来填充元素
     */
    public void moveZeroes(int[] nums) {
        //思路01: 遇到非0就交换到最前端,指针l维护已经交换好的元素
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != 0) {
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l++] = temp;     //只有交换，才指向下一个元素，注意指向的元素是没有排好序的元素！！！@
            }
        }
    }

    public void moveZeroes02(int[] nums) {
        //思路02: 遇到非0就填充到最前端，最后用0填充剩余部分
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != 0) {
                nums[l++] = nums[r];
            }
        }
        while (l < nums.length) nums[l++] = 0;
    }


    /**
     * 题目07(leetcode 第647题): 回文子串
     * 描述:给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * eg:输入："aaa" --> 输出：6  (6个回文子串: "a", "a", "a", "aa", "aa", "aaa")
     * <p>
     * 思路01(中心扩散):
     * 思路02: todo 动态规划
     */
    int res03 = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            centralExpand(i, i, s);          //以i为中心,这是奇数的情况
            centralExpand(i, i + 1, s);   //以i和i+1为中心,这是偶数的情况
        }
        return res03;
    }

    void centralExpand(int l, int r, String s) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            res03++;
            l--;
            r++;
        }
    }

    /**
     * 题目08(leetcode 第448题): 找到所有数组中消失的数字
     * 描述: 给你一个含 n 个整数（索引是0 ~ n-1）的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
     * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * eg:
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[5,6]
     * <p>
     * 输入：nums = [1,1]
     * 输出：[2]
     * <p>
     * 思路01（暴力解）: 遍历1~n，从数组中寻找对应的值，如果找不到就加入到list中，时间复杂度是o(n2)
     * 思路02(Set):先把arr加入到hashSet中，在遍历1~n，不在set就加入到队列中
     * 思路03(官方,最优解): 如果遇到就在对应的num-1,对应index处加入+n,在次遍历1~n，如果该index不大于n，就不在数组中
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {     //先使用HashSet记录1~n在遍历判断
        LinkedList<Integer> res = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int temp = (nums[i] - 1) % n;
            nums[temp] += n;            //这里+n，在上面%n就不会影响前面的操作
        }
        for (int i = 0; i < n; i++) {
            //如果该点index没有被加，所以不存在index+1的值
            if (nums[i] <= n) res.add(i + 1);
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers01(int[] nums) {
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) res.add(i);
        }
        return res;
    }
}
