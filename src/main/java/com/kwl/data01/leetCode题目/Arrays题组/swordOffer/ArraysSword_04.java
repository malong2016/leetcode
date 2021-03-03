package com.kwl.data01.leetCode题目.Arrays题组.swordOffer;

/**
 * 剑指offer_数组_题组04(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/18
 */
public class ArraysSword_04 {

    /**
     * 题目1(swordOffer 面试题3题目一): 数组中重复的数字(可以修改数组)
     * 描述: 在一个长度为n的数组中所有数字都是在0~n-1范围内。数组中某些数字是
     * 重复的，但是不知道有几个数字是重复的，也不知道每个数字重复了几次。请
     * 找出数组中任意的一个重复的数字。例如，如果输入长度为为7的数组{2,3,1,0,2,5,3}
     * 那么对应输出的重复的数字是2或者3
     * <p>
     * 思路01(leetcode): HashSet不断的加入,遇到重复的元素就输出
     * 思路02(leetcode): 原地交换,将这个元素换到对应的index下（value = 2,换到index=2下）,如果遇到arr[num[i]]=num[i]就输出num[i]
     */
    public static int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i]; //这说明对应的位置上存在num[i] = i
            int temp = nums[i];       //同时记录下a的值和b的下标
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;     //找不到就返回-1
    }

    /**
     * 题目2(swordOffer 面试题3题目二): 数组中重复的数字(不修改数组)  todo
     * 描述: 在一个长度为n的数组中所有数字都是在0~n-1范围内。数组中某些数字是
     * 重复的，但是不知道有几个数字是重复的，也不知道每个数字重复了几次。请
     * 找出数组中任意的一个重复的数字。例如，如果输入长度为为7的数组{2,3,1,0,2,5,3}
     * 那么对应输出的重复的数字是2或者3
     */

    /**
     * 题目3(swordOffer 面试题4): 二维数组中的查找
     * 描述: 在一个二维数组中,每一行都是按照从左到右的递增顺序排列,每一列都是按照从上到下递增顺序排序。
     * 请完成一个函数,输入这样的一个二维数组和一个整数,判断数组中是否含有这样的整数
     * eg:
     * {1, 2, 8,   9}
     * {2, 4, 9,  12}
     * {4, 7, 10, 13}
     * {6, 8, 11, 15}
     * <p>
     * 思路01: 从有下角(i,j)开始遍历,如果该值>targetValue,i--,如果该值<targetValue,j++
     */
    public static boolean findNumberIn2DArray(int[][] arr, int targetValue) {
        if (arr==null) return false;        //传入null,返回false
        int i = arr.length - 1, j = 0;
        while (i >= 0 && j < arr[0].length) {
            if (arr[i][j] > targetValue) i--;           //二维数组第一个是行,第二个是列
            else if (arr[i][j] < targetValue) j++;
            else return true;
        }
        return false;
    }

    /**
     * 题目4(swordOffer 面试题19):正则表达式的匹配
     * 描述: 请实现一个函数来匹配,'.'代表任意一个字符,'*'代表0个后者任意长度的字符
     * 判断二个字符是否匹配, 'a.a'和'ab*ac*a'匹配    'aa.a'和'ab*a'不匹配
     *
     * 思路01:动态规划 todo理解
     */
    public static boolean match(String s,String p){
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for(int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        // 状态转移
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(p.charAt(j - 1) == '*') {
                    if(dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if(dp[i][j - 1]) dp[i][j] = true;                                       // 2.
                    else if(dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 3.
                    else if(dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 4.
                } else {
                    if(dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if(dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
//        System.out.println("题目1(swordOffer 面试题3题目一): 数组中重复的数字(可以修改数组):");
//        System.out.println(findRepeatNumber(new int[]{1, 2, 3, 1}));

//        int[][] in2DArray = {
//                {1, 2, 8, 9},
//                {2, 4, 9, 12},
//                {4, 7, 10, 13},
//                {6, 8, 11, 15}
//        };
//        System.out.println("题目3(swordOffer 面试题4): 二维数组中的查找");
//        System.out.println(findNumberIn2DArray(in2DArray, 15));


    }
}
