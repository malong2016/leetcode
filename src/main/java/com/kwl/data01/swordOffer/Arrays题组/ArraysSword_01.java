package com.kwl.data01.swordOffer.Arrays题组;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 剑指offer_数组_题组01(每个题组是8个题目,本组7个题目)
 *
 * 题目1(swordOffer 面试题21): 输入一个整数数组,一个方法让奇数在arr的前半部分,偶数在arr的后半部分
 * 题目2(swordOffer 面试题53): 数字在排序数组中出现的次数
 * 题目3(swordOffer 面试题57题目1): 和为s的二个数字
 * 题目4(swordOffer 面试题57题目2):和为s的连续正数序列
 * 题目5(swordOffer 面试题45): 输入一个正整数数组，把数组里所有的数字拼接起来排成一个数,打印能拼接处的所有数字中最小的一个。
 * 题目6(swordOffer 面试题56): 数组中数字出现的次数
 * 题目7(swordOffer 面试题61): 扑克牌中顺子
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class ArraysSword_01 {

    /**
     * 题目1(swordOffer 面试题21):输入一个整数数组,一个方法让奇数在arr的前半部分,偶数在arr的后半部分
     * <p>
     * 思路01:暴力解,就是顺序扫描数组,遇到奇数就让前面的数前面向后移动一位,在将这个数插入到开头
     * 时间复杂度是0(n2)不推荐
     * 思路02:设置一个首尾指针,pEnd>pBegin之前,pBegin从头开始扫描,pEnd从尾部向前扫描,pBegin扫描到偶数,pEnd扫描到奇数
     * 就交互数组
     */
    public static void reorderOddEven(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int pBegin = 0, pEnd = arr.length - 1;
        while (pBegin < pEnd) {
            while (pBegin < pEnd && !isEven(arr[pBegin])) pBegin++;     //是奇数就右移动
            while (pBegin < pEnd && isEven(arr[pEnd])) pEnd--;          //是偶数就左移动

            if (pBegin < pEnd) {        //pBegin == end时候，不需要交换
                int temp = arr[pBegin];
                arr[pBegin] = arr[pEnd];
                arr[pEnd] = temp;
            }
        }
    }

    public static boolean isEven(int n) {        //判断是否是偶数,也是一种抽取,如果面试题目改变负数和非负数等等都是可以在这里改变
        return n % 2 == 0;
    }

    /**
     * 题目2(swordOffer 面试题53):数字在排序数组中出现的次数
     * 描述: 统计一个数字在排序数组中出现的次数。例如,输入排序数组{1,2,3,3,3,3,4,5}和数字3,数组3出现了四次,因此输出4
     *
     *
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
            if (mid == arr.length - 1 || arr[mid + 1] != k ) return mid; //不能等于
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
     * 题目3(swordOffer 面试题57题目1): 和为s的二个数字
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
        while (begin < end){
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
     * 题目4(swordOffer 面试题57题目2):和为s的连续正数序列
     * 描述: 输入一个正数s,打印出所有和为s的连续正数序列(至少要二个数).
     * 例如,输入15,由于1+2+3+4+5=4+5+6=7+8=15,所以打印出3个连续序列1~5 4~6 7~8
     * <p>
     * 思路01: 标记small,big,如果之和小于s,big++,大于s,small--,等于s,大于
     */
    public static void findContinuousSequence(int sum) {
        if (sum < 3) return;       //小于3不存在
        int small = 1;
        int big = 2;
        int middle = (1 + sum) / 2;      //>=middle起点之后就不可能存在连续((1+sum)/2比sum一半要大,所以不可能以这个为起点之后)
        int curSum = small + big;

        while (small < middle) {
            if (curSum == sum) printContinuousSequence(small, big);
            while (curSum > sum && small < middle) {       //要加到比sum大,或者直接加到small等于middle
                curSum -= small;
                small++;
                if (curSum == sum) printContinuousSequence(small, big);
            }
            big++;
            curSum += big;
        }
    }

    public static void printContinuousSequence(int small, int big) {
        for (int i = small; i < big + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");     //换行
    }

    /**
     * 题目5(swordOffer 面试题45)
     * 描述: 输入一个正整数数组，把数组里所有的数字拼接起来排成一个数,
     * 打印能拼接处的所有数字中最小的一个。
     * eg: 输入数组{3,32,321}就打印出这三个数字能排成的最小数字321323
     * <p>
     * 思路01(leetcode): 本质上是一个排序问题,从小到大的排序
     * x+y>y+x --> x>y   x+y<y+x -->y>x
     * 我们使用简单的内置函数来处理,如果要使用快速排序,请参考swordOffer和leetcode
     */
    public static int printMinNumber(int[] arr) {
        String[] strArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);   //将int类型转化为string类型进行放入strArr数组中
        }
        Arrays.sort(strArr, (x, y) -> (x + y).compareTo(y + x));    //会改变函数
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strArr) {
            stringBuilder.append(s);
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    /**
     * 题目6(swordOffer 面试题56): 数组中数字出现的次数
     * 描述: 一个整型数组中除了二个数字之外,其他数字都出现了二次,请写程序找出这个二个只出现一次的数字
     * 。要求时间复杂度是o(n),空间复杂度是o(1)
     * <p>
     * 思路01(leetcode):全员进行异或操作就可以,如果二个数相同异或操作之后就会变成0,不同异或操作就会1
     * 在计算过程中,成对出现的数字所有位会二二抵消为0,最后得到的结果就是那个出现一次的数字
     * <p>
     * 异或补充: x^0 = x; x^x = 0;
     */
    public static int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;                  //  这个是拿到a^b
        }
//        int div = 1;
//        while ((div & ret) == 0) {
//            div<<=1;
//        }
        int low_bit = ret & (-ret);  //拿到从从右到左,最近的一个1!!! 1000这种,因为是异或,所以这个位a,b一定是不同的
        int a = 0, b = 0;
        for (int n : nums) {
//            if ((div & n) != 0) {
            if ((low_bit & n) != 0) {   //首先如果数是相同,肯定是分到同一组,连续^(异或)为0,不影响
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }
    /**
     * 题目6(swordOffer 面试题56II): 数组中数字出现的次数
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     */

    /**
     * 题目7(swordOffer 面试题61): 扑克牌中顺子
     * 描述: 从扑克牌中抽取5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2~10为数字本身，A为1,J为11,,Q是12,K是13,大小王是0可以看成任意数字
     *
     *
     * 核心: 1 无重复 2除掉大小王 max - min < 5
     * 思路01(leetcode):核心是五张牌中没有重复元素,除了0之外max-min<5就满足是顺子,
     * 我们利用HashSet对数组中的元素进行判断其是否重复,同时记录除了0之外的最大值和最小值
     * 思路02(leetcode):先对数组进行排序,在arr[i+1]=arr[i]是返回false,max-min<5就满足是顺子,
     */
    public static boolean  isContinuous(int[] arr) {
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

    public static boolean isContinuous01(int[] arr) {
        Arrays.sort(arr);
        int joker = 0;       //这个记录非0的第一个index
        for (int i = 0; i < 4; i++) {
            if (arr[i] == 0) joker++;       //joker只有二张牌
            else if (arr[i] == arr[i+1]) return false;
        }
        return arr[4] - arr[joker] < 5;
    }
}
