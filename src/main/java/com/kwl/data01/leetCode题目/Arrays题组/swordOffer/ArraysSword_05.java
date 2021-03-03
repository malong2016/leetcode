package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

/**
 * 剑指offer_数组_题组05(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
public class ArraysSword_05 {

    /**
     * 题目1(swordOffer 面试题11):旋转数组的最小数字
     * 描述: 输入一个递增的arr,将最开始的若干个元素搬到数组的末尾
     * {3,4,5,1,2} 是 {1,2,3,4,5}的一个旋转数组
     * <p>
     * 思路01(leetcode 的二分法): 设置一个首尾指针i,j,在i!=j之前,用arr[(i+j)/2]和arr[j]比较
     * arr[mid]>arr[j] i=mid+1 (缩小范围)
     * arr[mid]<arr[j] j = mid(注意可能是mid,所以不能mid-1)
     * arr[mid]=arr[j] j--
     */
    public static int spanMin(int[] nums) {
        if (nums == null) return -1; //如果arr是null,就返回null
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] > nums[j]) i = mid + 1;     //缩小范围寻找,在下半区
            else if (nums[mid] < nums[j]) j = mid;
            else --j;
        }
        return nums[j];
    }

    /**
     * 题目2(swordOffer 面试题44):数字序列中某一位数字
     * 描述: 数字以0123456789101112131415....的序列化到一个字符序列中
     * 在这个序列中第5位(从0开始计算)是5,第13位是1,第19位是4.
     * 请写一个函数,求任意第n位对应的数字  //todo 找规律理解!!!
     */
    public static int digitAtIndex(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    /**
     * 题目3(swordOffer 面试题46):把数字翻译成字符串
     * 题目: 给定一个数字 0翻译为'a',1翻译为'b',11翻译为'l',25翻译为'z'
     * 一个数字经常有多个翻译,12258有五种翻译: bccfi,bwfi,bczi,mcfi,mzi
     * 编写一个函数用来计算一个数字有多少种不同的翻译情况
     * <p>
     * 思路01(leetcode 动态规划): f(i) = f(i-2)+f(i-1) 如果Xi-1Xi可以被翻译 f(i) = f(f-1),如果Xi-1Xi不能被翻译
     * 思路02(数字求余): todo等下
     */
    public static int getTranslationCount(int number) {
        String s = String.valueOf(number);
        int a = 1, b = 1;       //a在前,b在后
        for (int i = 2; i < s.length(); i++) {
            String tem = s.substring(i - 2, i);
            int c = tem.compareTo("10") >= 0 && tem.compareTo("25") <= 0 ? a + b : a;     //如果是10<=x<=25之间就是累计前面的f(n-1)+f(n-2)
            b = a;
            a = c;
        }
        return a;
    }

    /**
     * 题目4(swordOffer 面试题47):礼物的最大价值
     * 描述: 给定一个二维数组m*n,每一格都放有一个礼物,每一礼物都有一定的价值,
     * 从左下角-->每次向右或者向下移动一格-->到达右下角,求最多能拿到多少价值的礼物?
     * <p>
     * {1,  10,  3,   8},
     * {12,  2,  9,   6},
     * {5,  7,   4,   11},
     * {3,  7,  16,   5}
     * <p>
     * 思路01(leetcode 动态规划): f(i,j) = max|f(i,j-1),f(i-1,j)|+grid(i,j)   注意:(i,j)是坐标是最后达到的地方!!!
     * 思路02(递归): todo 去评论区看看!!!
     */
    public static int getMaxValueSolution(int[][] grib) {
        int m = grib.length, n = grib[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;      //第一行第一列的情况
                if (i == 0) grib[i][j] += grib[i][j - 1];     //第一行的情况
                else if (j == 0) grib[i][j] += grib[i - 1][j];
                else grib[i][j] += Math.max(grib[i][j - 1], grib[i - 1][j]);
            }
        }
        return grib[m - 1][n - 1];
    }

    public static int maxValue(int[][] grid) {           //不需要每次循环都要判断!!!!执行效率更高
        int m = grid.length, n = grid[0].length;
        for (int j = 1; j < n; j++) // 初始化第一行,第一行的长度由多少列决定(grid[0].length)！！！
            grid[0][j] += grid[0][j - 1];
        for (int i = 1; i < m; i++) // 初始化第一列,第一列的长度由多少行决定(grid.length)
            grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        return grid[m - 1][n - 1];
    }

    public static int maxValueRecur(int[][] grid) {   //方法三:递归法.leetcode是超时的
        return recur(grid,grid.length-1,grid[0].length-1);
    }

    public static int recur(int[][] grid, int m, int n) {
        if(m==0&&n==0) return grid[m][n];
        else if(m==0)  return grid[m][n]+recur(grid,m,n-1);   //第一行的情况
        else if(n==0)  return grid[m][n]+recur(grid,m-1,n);   //第一列的情况
        else return grid[m][n]+Math.max(recur(grid,m-1,n),recur(grid,m,n-1));
    }

    public static void main(String[] args) {
//        System.out.println("题目1(swordOffer 面试题11):旋转数组的最小数字:");
//        System.out.println(spanMin(new int[]{3, 4, 5, 1, 2}));

//        System.out.println("题目3(swordOffer 面试题46):把数字翻译成字符串:");
//        System.out.println(getTranslationCount(12258));

        System.out.println("题目4(swordOffer 面试题47):礼物的最大价值");
        int[][] arr2Wei = {         //这个arr2Wei会影响!!!dp每个值都换成了最长路径!!
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
//        System.out.println("动态规划法:" + maxValue(arr2Wei));
        System.out.println("递归法:" + maxValueRecur(arr2Wei));
    }
}
