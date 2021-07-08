package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 剑指offer_数组_题组03(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class ArraysSword_03 {

    /**
     * 题目01(swordOffer 第11题):旋转数组的最小数字
     * 描述: 输入一个递增的arr,将最开始的若干个元素搬到数组的末尾
     * {3,4,5,1,2} 是 {1,2,3,4,5}的一个旋转数组
     * <p>
     * 思路01(leetcode 的二分法):  本质上就是寻找最小值（可能存在重复值）
     * 设置一个首尾指针i,j,在i!=j之前,用arr[(i+j)/2]和arr[j]比较
     * arr[mid]>arr[j] i=mid+1 (缩小范围)
     * arr[mid]<arr[j] j = mid(注意可能是mid,所以不能mid-1)
     * arr[mid]=arr[j] j--
     */
    public  int spanMin(int[] nums) {
        if (nums == null) return -1; //如果arr是null,就返回null
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) low = mid + 1;    //上半区,注意这里一定是在上半区，所以mid+1(最差就是high)
            else if (nums[mid] < nums[high]) high = mid;  //下半区，可能就是mid
            else --high;         //向下逼近,mid一定是小于high的
        }
        return nums[high];
    }

    /**
     * 题目02(swordOffer 第44题): 数字序列中某一位的数字
     * 描述: 数字以0123456789101112131415....的序列化到一个字符序列中
     * 在这个序列中第5位(从0开始计算)是5,第13位是1,第19位是4.
     * 请写一个函数,求任意第n位对应的数字
     */
    public  int digitAtIndex(int n) {
        int digit = 1;            //是几位数(位数)
        long start = 1;          //从哪一位开始 (一定是long)
        long count = 9;         //本组存在几位数 (1~9, 10~99, 100~999)(一定是long)
        while (n > count) {
            n -= count;
            digit += 1;     //1位数, 2位数, 3位数
            start *= 10;   //1 10 100 1000 ,9*start就是本组的个数
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }


    /**
     * 题目07(swordOffer 第59题-I): 滑动窗口的最大值
     * 描述: 给定一个数组和滑动窗口的大小,找出所有在滑动窗口的最大值
     * {2,3,4,2,6,2,5,1}滑动窗口3 --> 最大值分别是{4,4,6,6,6,5}    //从k开始都有一个滑动窗口，res的长度是length-k+1
     * <p>
     * 思路01:暴力解,时间复杂度是o(n)
     * 思路02:利用一个双端队列Deque维护,
     * 1)仅仅含有窗口内的元素: 每轮窗口滑动移除了元素 nums[i - 1]nums[i−1] ，需将 dequedeque 内的对应元素一起删除。
     * 2)非严格递减: 每轮窗口滑动添加了元素 nums[j + 1]nums[j+1] ，需将 dequedeque 内所有 < nums[j + 1]<nums[j+1] 的元素删除。
     * 3) 每次都是队首就是最大值
     */
    public int[] maxSildingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        LinkedList<Integer> linkedList = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 1 - k, j = 0; j < nums.length; i++, j++) {
            if (i > 0 && linkedList.peekFirst() == nums[i - 1])
                linkedList.removeFirst();    //如果num[i-1](对应滑动过的窗口)和队列中队首相等就删除
            while (!linkedList.isEmpty() && linkedList.peekLast() < nums[j])
                linkedList.removeLast(); //保持linkedList递减,删除队列中所有比Num[j]小的数
            linkedList.addLast(nums[j]);
            if (i >= 0) res[i] = linkedList.peekFirst();     //记录窗口的最大值
        }
        return res;
    }

    /**
     * 题目08(swordOffer 第61题): 扑克牌中的顺子
     * 描述: 从扑克牌中抽取5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2~10为数字本身，A为1,J为11,,Q是12,K是13,大小王是0可以看成任意数字
     * <p>
     * <p>
     * 核心: 1 无重复 2除掉大小王 max - min < 5
     * 思路01(leetcode):核心是五张牌中没有重复元素,除了0之外max-min<5就满足是顺子,
     * 我们利用HashSet对数组中的元素进行判断其是否重复,同时记录除了0之外的最大值和最小值
     * 思路02(leetcode):先对数组进行排序,在arr[i+1]=arr[i]是返回false,max-min<5就满足是顺子,
     */
    public  boolean isContinuous(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        int min = 14, max = 0;
        for (int i : arr) {
            if (i == 0) continue;   //如果是0,就跳出循环,不统计
            max = Math.max(max, i);
            min = Math.min(min, i);
            if (!hashSet.add(i)) return false;   //如果有重复元素就返回false
        }
        return max - min < 5;
    }

    public  boolean isContinuous01(int[] arr) {
        Arrays.sort(arr);
        int joker = 0;       //这个记录非0的第一个index
        for (int i = 0; i < 4; i++) {
            if (arr[i] == 0) joker++;       //joker只有二张牌
            else if (arr[i] == arr[i + 1]) return false;
        }
        return arr[4] - arr[joker] < 5;
    }

}
