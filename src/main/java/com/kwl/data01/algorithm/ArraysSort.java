package com.kwl.data01.algorithm;

import java.util.Arrays;

/**
 * 数组排序 输入理解
 *
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class ArraysSort {

    /**
     * 排序算法汇总:
     *   插入排序类:   简单插入排序,折半插入排序,希尔排序
     *   交换排序类:   冒泡排序,快速排序
     *   选择排序类:   简单选择排序,堆(大顶堆、小顶推)排序
     *   归并排序类:   归并排序
     *
     *
     * 时间复杂度:
     *   时间复杂度o(n2): 简单插入排序 冒泡排序 简单选择排序 折半插入排序 希尔排序
     *   时间复杂度o(nlog2n): 快速排序 堆(大顶堆、小顶推)排序 2-路归并排序
     *
     *
     * 空间复杂度:
     *   空间复杂度o(log2n): 快速排序
     *   空间复杂度o(n): 2-路归并排序
     *   空间复杂度o(l): 其他排序
     */


    /**
     * 1.1 交换排序之冒泡排序
     */
    public int[] bubbleSort(int[] arr) {             //升序,每次冒泡出最大元素
        if (arr == null || arr.length <= 1) return arr;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 1.2 交换排序之快速排序
     */
    public static int[] quickSort(int[] A, int low, int high) {
        if (low < high) {           //如果是只有一个元素就没有必要快速排序,递归结束!!!!
            int mid = partition(A, low, high);        //排好第一趟,而且取出中心pivot
            quickSort(A, low, mid - 1);         //将左边排好
            quickSort(A, mid + 1, high);        //将右边排好
        }
        return A;
    }

    public static   int partition(int[] A, int low, int high) {    //partition 分割/隔板
        int pivot = A[low];
        while (low < high) {       //最后low和high双指针汇合结束
            while (low < high && A[high] >= pivot) high--;    //从high开始扫描,如果大于pivot&&大于low,high指针向左移动
            A[low] = A[high];               //移动到小于pivot,将这个数赋值给A[low](注意A[low]是已经被交换的数或者pivot,不会覆盖排序字段)
            while (low < high && A[low] <= pivot) low++;          //同上从左向右扫描,理由同上
            A[high] = A[low];
        }
        A[low] = pivot;         //pivot最后存放的位置
        return low;             //最后左右指针移动到一起,就是pivot最后的index
    }


    /**
     * 2.1 选择排序之简单选择排序
     * 每次选择出最小的一位,放在最左边,最后按照升序排序
     */
    public  int[] changeSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int t = i;        //默认记录是本身
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[t] > arr[j]) t = j;     //如果记录点比比较点大,则记录点换成比较点
            }
            if (t != i) {          //找出每次循环的最小值,如果不是本身,就交换
                int temp = arr[i];
                arr[i] = arr[t];
                arr[t] = temp;
            }
        }
        return arr;
    }
    /**
     * 2.1 选择排序之堆排序
     *
     * 先建立一个大顶推，然后将堆顶元素和最后元素进行交换。
     */

    /**
     * 3.1 插入排序之直接插入排序(升序)
     * 思路:遍历后面的数组的元素,不断插入到前面已经拍好序的数组
     */
    public  int[] directInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int t = arr[i];       //待插入数字
            int j;
            for (j = i - 1; j >= 0 && arr[j] > t; j--) {        //如果待插入元素是最小的,j=-1会跳出循环
                arr[j + 1] = arr[j];   //向后移动一位,本位会空置
            }
            arr[j + 1] = t;           //如果待插入元素>=拍好序元素,直接插入到前端(已经空出位置)

        }
        return arr;
    }

    /**
     * 3.2 插入排序之折半插入排序
     * 时间复杂度是o(nlog2n)
     * 思路:综合折半查找+插入排序,每次插入前先折半查到到插入位置,然后具体后移动一位,提高时间复杂度
     */
    public  void halfInsertSout(int[] arr) {         //注意方法能改变待排序数组(数组本质上是引用!!!!)
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            int low = 0, high = i - 1;    //确定已经拍好序的首尾
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }             //最后指向的位置就是该元素要插入的位子,先集体右移动,向后复制,不会覆盖前面的值!!!
            for (int j = i - 1; j >= low; j--) {              //my low是插入点
                arr[j + 1] = arr[j];
            }
            arr[low] = temp;
//            for (int j = i - 1; j >= high + 1; j--) {        //王道 high+1是插入点
//                arr[j + 1] = arr[j];
//            }
//            arr[high + 1] = temp;       //移动完毕之后,将待插入元素temp赋值给空位
        }
    }


    /**
     * 3.3 插入排序之希尔排序(升序)  类比二路归并排序
     * eg: [8,9,1,7,2,3,5,4,6,0]
     * 1) 初始增量gap = length/2 分为五组[8,3] [9,5] [1,4] [7,6] [2,0],对每组进行直接插入排序
     * 2) gap = length/2/2 分两组[3,1,0,9,7]  [5,6,8,4,2] 进行直接插入排序
     * 3) gap= 1,分为一组, [0,2,1,4,3,5,7,6,9,8]直接插入排序
     */
    public void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {     //逐步分组
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tmp = arr[j];   //待排序,下面是找位置
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        arr[j] = arr[j - gap];    //移动
                        j -= gap;
                    }
                }
                arr[j] = tmp;
            }
        }
    }
}
