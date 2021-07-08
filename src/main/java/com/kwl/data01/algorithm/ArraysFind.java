package com.kwl.data01.algorithm;

/**
 * 数组(Arrays)查找: 二分查找和顺序查找
 *
 * @author kuang.weilin
 * @date 2021/2/10
 */
public class ArraysFind {

    /**
     * 折半(二分)查找(适用于顺序表)
     * return: 找不到返回-1,找的到返回下标
     * 时间复杂度:o(log2n)
     */
    public int halfFind(int[] arr, int targetValue) {
        int high = arr.length - 1;       //初始最高位为数组的长度,最低位为0
        int low = 0;
        while (low <= high) {         //注意可以等于
            int mid = (low + high) / 2;
            if (arr[mid] > targetValue) {       //如果在中间的值大于目标值,向左下半查找
                high = mid - 1;
            } else if (arr[mid] < targetValue) {  //向右下半查找
                low = mid + 1;
            } else {                           //找到
                return mid;
            }
        }
        return -1;
    }

    /**
     * 顺序查找
     * 时间复杂度 o(n)
     */
    public int orderFind(int[] arr, int targetValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetValue) return i;
        }
        return -1;
    }
}
