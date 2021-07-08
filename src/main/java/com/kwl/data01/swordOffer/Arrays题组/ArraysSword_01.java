package com.kwl.data01.swordOffer.Arrays题组;

import java.util.*;

/**
 * 剑指offer_数组_题组01(每个题组是8个题目,本组7个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class ArraysSword_01 {

    /**
     * 题目01(swordOffer 第21题): 调整数组顺序使奇数位于偶数前面
     * <p>
     * 思路01:暴力解,就是顺序扫描数组,遇到奇数就让前面的数前面向后移动一位,在将这个数插入到开头
     * 时间复杂度是0(n2)不推荐
     * 思路02:设置一个首尾指针,pEnd>pBegin之前,pBegin从头开始扫描,pEnd从尾部向前扫描,pBegin扫描到偶数,pEnd扫描到奇数
     * 就交互数组
     */
    public static void reorderOddEven(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int begin = 0, end = arr.length - 1;
        while (begin < end) {
            while (begin < end && arr[begin] % 2 == 1) begin++;     //是奇数就右移动
            while (begin < end && arr[begin] % 2 == 0) end--;          //是偶数就左移动

            if (begin < end) {        //pBegin == end时候，不需要交换
                int temp = arr[begin];
                arr[begin] = arr[end];
                arr[end] = temp;
            }
        }
    }

    /**
     * 题目02(swordOffer 第53题-I): 数字在排序数组中出现的次数  todo 暂时只能想到暴力解
     * 描述: 统计一个数字在排序数组中出现的次数。例如,输入排序数组{1,2,3,3,3,3,4,5}和数字3,数组3出现了四次,因此输出4
     * <p>
     * <p>
     * 思路01(基础解): 先二分查找到3,在左右扫描统计3出现的次数,时间复杂度是o(n)
     * 思路02(最优解,时间复杂度o(log2n)): 先二分查到到3,如果k左边第一个元素不是k(或者改元素下标是0),那么这个元素就是第一个k,返回这个元素的index
     * 左边存在,在递归找左边,找到满足要求
     */
    public static int getFirstK(int[] arr, int k, int start, int end) { //递归开始和结束的位置
        if (start > end) return -1;       //不存在k,返回-1
        int mid = (start + end) / 2;
        if (arr[mid] == k) {
//            if (mid > 0 && arr[mid - 1] != k || mid == 0) return mid;
            if (mid == 0 || arr[mid - 1] != k) return mid;
            else end = mid - 1;    //向下找
        } else if (arr[mid] > k) {          //左半区找
            end = mid - 1;
        } else {                            //右半区找
            start = mid + 1;
        }
        return getFirstK(arr, k, start, end);
    }

    public static int getLastK(int[] arr, int k, int start, int end) {      //理由同上
        if (start > end) return -1;       //不存在k,返回-1
        int mid = (start + end) / 2;
        if (arr[mid] == k) {
            if (mid == arr.length - 1 || arr[mid + 1] != k) return mid; //不能等于
            else start = mid + 1;    //向上找
        } else if (arr[mid] > k) {          //左半区找
            end = mid - 1;
        } else {                            //右半区找
            start = mid + 1;
        }
        return getLastK(arr, k, start, end);
    }

    public static int getNumberOfK(int[] arr, int k) {
        int number = 0;
        if (arr != null) {
            int firstK = getFirstK(arr, k, 0, arr.length - 1);
            int lastK = getLastK(arr, k, 0, arr.length - 1);

            if (firstK > -1 && lastK > -1) number = lastK - firstK + 1;
        }
        return number;        //注意arr如果是null,就返回初始化0
    }

    /**
     * 题目03(swordOffer 第53题-II): 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
     * 并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 思路01: 以缺少的值为边界，左半区是nums[i] = i,右半区是num[i] != i 题目转化为求右子数组的首位元素
     */
    public int missingNumber(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == mid) low = mid + 1; //此时中点在左半区，向上半区逼近
            else high = mid - 1;      //中点是在右半区，下半区逼近
        }
        return low;
    }

    /**
     * 题目04(swordOffer 第57题-I): 和为s的二个数字
     * 描述:输入一个递增排序的数组和一个数字s(s>0),在数组中查找二个数,
     * 使得他们的和正好是s。如果有多对数字的和等于s,就输出任意一对就可以
     * eg: {1,2,4,7,11,15}和数字15 => 输入 {4,11} (因为4+11=15)
     * <p>
     * 思路01(时间复杂度o(n)):设置front/tail指针,计算之和,大于目标值,tail指针右移动,小于目标值,front左移动
     * 等于就返回头尾指针指向的数  findNumbersWithSum
     * 思路02: 如果set里面存在target-nums[i],就直接输出，如果不存在就把nums[i] add到set里面
     */
    public int[] findNumbersWithSum(int[] nums, int target) {
        if (nums == null || nums.length == 1) return new int[0];
        int begin = 0, end = nums.length - 1;
        while (begin < end) {
            int sum = nums[begin] + nums[end];
            if (sum > target) end--;
            else if (sum < target) begin++;
            else {
                return new int[]{nums[begin], nums[end]};
            }
        }
        return new int[0];
    }

    public int[] findNumbersWithSum01(int[] nums, int target) {
        if (nums == null || nums.length == 1) return new int[0];
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            if (set.contains(target - num)) return new int[]{num, target - num};
            else set.add(num);
        }
        return new int[0];
    }


    /**
     * 题目05(swordOffer 第57题-II): 和为s的连续正数序列
     * 描述: 输入一个正数s,打印出所有和为s的连续正数序列(至少要二个数).
     * 例如,输入15,由于1+2+3+4+5=4+5+6=7+8=15,所以打印出3个连续序列1~5 4~6 7~8
     * <p>
     * 思路01: 可以使用数学公式求解(首+末)/2=和,反过来推导--参考评论区
     * 思路02: 利用滑动窗口,如果窗口和s是大于或者等于sum的，那么right--,如果窗口和是小于sum，left++；
     */
    public int[][] findContinuousSequence(int target) {
        if (target < 3) return new int[0][0];  //最少要连续二个数,这个不符合条件
        int left = 1, right = 2, sum = 3;
        List<int[]> res = new LinkedList<>();      //注意本题目返回的是int[][]所以泛型还是使用int[]好
        while (left < right) {
            if (sum == target) {
                int[] path = new int[right - left + 1];
                for (int i = left; i < right + 1; i++)
                    path[i - left] = i;  //把联系的数写入path之中
                res.add(path);
            }
            if (sum >= target) {        //先减少后移动
                sum -= left;
                left++;
            } else {                    //先移动,后添加
                right++;
                sum += right;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 题目06(swordOffer 第45题): 把数组排成最小的数
     * 描述: 输入一个正整数数组，把数组里所有的数字拼接起来排成一个数,
     * 打印能拼接处的所有数字中最小的一个。
     * eg: 输入数组{3,32,321}就打印出这三个数字能排成的最小数字321323
     * <p>
     * 思路01(leetcode): 本质上是一个排序问题,从小到大的排序
     * x+y>y+x --> x>y   x+y<y+x -->y>x
     * 思路02: 有内置函数排序和快速排序二种方法，快速排序参考leetcode的k神！！
     */
    public String printMinNumber(int[] nums) {
        //对x和y进行拼接字符串,如果x + y >  y + x,那么x > y,反之 x < y
        //所以我们的思路就是全部转化为string,在利用Java内置的排序函数进行排序，在加入
        //到StringBuild中去,最后转化为String类型
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = String.valueOf(nums);   //整型类型转化为String类型
        }
        Arrays.sort(numStr, (x, y) -> (x + y).compareTo(y + x)); //对String[]进行排序
        StringBuilder res = new StringBuilder();
        for (String string : numStr) {
            res.append(string);
        }
        return res.toString();
    }

    /**
     * 题目07(swordOffer 第56题-I): 数组中数字出现的次数
     * 描述: 一个整型数组中除了二个数字之外,其他数字都出现了二次,请写程序找出这个二个只出现一次的数字
     * 。要求时间复杂度是o(n),空间复杂度是o(1)
     * <p>
     * 思路01(leetcode): 全员异或得到x ^ y,在利用循环找出倒数第一个为1的数，利用这个位置进行分组，就可以得到最后的值
     * 用 00100这种去分组一定是x&y==0这种判断
     * <p>
     * 异或补充: x^0 = x; x^x = 0;
     */
    public static int[] singleNumbers(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n ^= num;            //求出x ^ y
        }
        int m = 1, x = 0, y = 0;
        while ((m & n) == 0) m <<= 1; //如果等于0(注意不是不等于1!!!)，那么1就继续向左移动,寻找第一位不是1的
        for (int num : nums) {
            if ((num & m) == 0) x ^= num;   //分组01
            else y ^= num;                  //分组02
        }
        return new int[]{x, y};
    }

    /**
     * 题目08(swordOffer 第56题-II): 数组中数字出现的次数
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * <p>
     * 思路01: 使用HashMap进行测试统计
     * 思路02：todo 利用位运算计算,暂时还没有处理
     */
    public int singleNumber02(int[] nums) {   //使用HashMap暴力解
        Map<Integer, Integer> map = new HashMap<>();       //key -- nums的值，value -- nums出现的次数
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1); //以前就存在，次数+1
            else map.put(num, 1);                 //第一次来,把次数设置为1
        }
        System.out.println("map = " + map);
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) return key;
        }
        return -1;
    }





}
