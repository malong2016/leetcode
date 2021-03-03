package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer_数组_题组02(每个题组是4个题目,本组二个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class ArraysSword_02 {


    /**
     * 题目1(swordOffer 面试题57题目1): 和为s的二个数字
     * 描述:输入一个递增排序的数组和一个数字s(s>0),在数组中查找二个数,
     * 使得他们的和正好是s。如果有多对数字的和等于s,就输出任意一对就可以
     * eg: {1,2,4,7,11,15}和数字15 => 输入 {4,11} (因为4+11=15)
     * <p>
     * 思路01(时间复杂度o(n)):设置front/tail指针,计算之和,大于目标值,tail指针右移动,小于目标值,front左移动
     * 等于就返回头尾指针指向的数
     */
    public static int[] findNumbersWithSum(int[] arr, int sum) {
        if (arr == null || arr.length == 1) return null;      //如果数组为null,就返回null
        int pFront = 0, pTail = arr.length - 1;    //头尾指针 ahead(前面) behind(后面)
        while (pFront < pTail) {
            if (arr[pFront] + arr[pTail] == sum) return new int[]{arr[pFront], arr[pTail]};
            else if (arr[pFront] + arr[pTail] > sum) --pTail;    //尾部移动
            else ++pFront;                             //头部移动
        }
        return null;      //如果跳出循环,就是找不到,直接返回null
    }

    /**
     * 题目1(swordOffer 面试题57题目2):和为s的联系正数序列
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
     * 题目2(swordOffer 面试题45)
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
     * 题目3(swordOffer 面试题56): 数组中数字出现的次数
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
     * 题目4(swordOffer 面试题61): 扑克牌的顺子
     * 描述: 从扑克牌中抽取5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2~10为数字本身，A为1,J为11,,Q是12,K是13,大小王可以看成任意数字
     * <p>
     * 思路01(leetcode):核心是五张牌中没有重复元素,除了0之外max-min<5就满足是顺子,
     * 我们利用HashSet对数组中的元素进行判断其是否重复,同时记录除了0之外的最大值和最小值
     * 思路02(leetcode):先对数组进行排序,在arr[i+1]=arr[i]是返回false,max-min<5就满足是顺子,
     */
    public static boolean  isContinuous(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        int min = 14, max = 0;      //这个初值要逆向思维，设置max尽可能小值
        for (int i : arr) {
            if (i == 0) continue;   //如果是0,就跳出循环,不统计
            max = Math.max(max, i);  //统计最大值和最小值
            min = Math.min(min, i);
            if (hashSet.contains(i)) return false;   //如果有重复元素就返回false
            hashSet.add(i);
        }
        return max - min < 5;
    }

   public static boolean isContinuous01(int[] arr) {
        Arrays.sort(arr);
        int joker = 0;       //这个记录非0的第一个index
        for (int i = 0; i < 4; i++) {
            if (arr[i] == 0) joker++;
            else if (arr[i] == arr[i+1]) return false;
        }
        return arr[4] - arr[joker] < 5;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 11, 15};
//        System.out.println("题目1:数组和一个数字s(s>0),在数组中查找二个数");
//        System.out.println(Arrays.toString(findNumbersWithSum(arr,15)));
//        System.out.println("题目2:和为s的联系正数序列");
//        findContinuousSequence(15);
//        System.out.println("题目2(swordOffer 面试题45)输入一个正整数数组，把数组里所有的数字拼接起来排成一个数");
//        System.out.println(printMinNumber(new int[]{3,32,321}));

//        System.out.println("swordOffer 面试题56,一个整型数组中除了二个数字之外,其他数字都出现了二次,请写程序找出这个二个只出现一次的数字");
//        System.out.println(Arrays.toString(singleNumbers(new int[]{1, 1, 2, 3, 4, 4})));

        System.out.println("题目3(swordOffer 面试题61): 扑克牌的顺子:");
        System.out.println(isContinuous(new int[]{0, 2, 3, 4, 6}));
        System.out.println(isContinuous01(new int[]{0, 2, 3, 4, 6}));

    }


}
