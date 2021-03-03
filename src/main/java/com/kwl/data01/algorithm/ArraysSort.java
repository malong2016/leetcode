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
     *   时间复杂度o(n2): 简单插入排序 冒泡排序 简单选择排序 折半插入排序
     *   时间复杂度o(nlog2n): 快速排序 堆(大顶堆、小顶推)排序 2-路归并排序
     *
     *
     * 空间复杂度:
     *   空间复杂度o(log2n): 快速排序
     *   空间复杂度o(n): 2-路归并排序
     *   空间复杂度o(l): 其他排序
     */


    /**
     * 1 1)交换排序之冒泡排序
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {  //传入为null,或者数组长度是0或者1,返回本身
            return arr;
        }
        //注意一共要冒泡arr.length-1次,for循环如果从0开始而且是<(小于) n,那么循环次数就是n (n-1+1)
        for (int i = 0; i < arr.length - 1; i++) {
            //从左向右冒泡,每次冒泡出最大值!!注意第一次来,不需要处理,直接拿倒数第二个数比较
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {               //如果左边大于右边,交换!!!
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        return arr;
    }

    /**
     * 1 2)交换排序之快速排序(注意数组属于引用,方法里面改变会影响外面)
     */
    public static int[] quickSort(int[] A, int low, int high) {
        if (low < high) {           //如果是只有一个元素就没有必要快速排序,递归结束!!!!
            int pivotpos = partition(A, low, high);        //排好第一趟,而且取出中心pivot
            quickSort(A, low, pivotpos - 1);         //将左边排好
            quickSort(A, pivotpos + 1, high);        //将右边排好
        }
        return A;
    }

    /**
     * 一趟选择排序,返回排序完成之后的基准(pivot)之后的下标索引(index)
     */
    public static int partition(int[] A, int low, int high) {
        int pivot = A[low];
        while (low < high) {       //最后low和high双指针汇合结束
            while (A[high] >= pivot && high > low) high--;    //从high开始扫描,如果大于pivot&&大于low,high指针向左移动
            A[low] = A[high];               //移动到小于pivot,将这个数赋值给A[low](注意A[low]是已经被交换的数或者pivot,不会覆盖排序字段)
            while (A[low] <= pivot && high > low) low++;          //同上从左向右扫描,理由同上
            A[high] = A[low];
        }
        A[low] = pivot;         //pivot最后存放的位置
        return low;             //最后左右指针移动到一起,就是pivot最后的index
    }


    /**
     * 2 1)选择排序之简单选择排序
     * 每次选择出最小的一位,放在最左边,最后按照升序排序
     */
    public static int[] changeSort(int[] arr) {
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
     * 2 2)选择排序之堆排序
     */

    /**
     * 3 1)插入排序之直接插入排序(升序)
     * 思路:遍历后面的数组的元素,不断插入到前面已经拍好序的数组
     */
    public static int[] directInsertSort(int[] arr) {
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
     * 3 2)插入排序之折半插入排序
     * 时间复杂度是o(nlog2n)
     * 思路:综合折半查找+插入排序,每次插入前先折半查到到插入位置,然后具体后移动一位,提高时间复杂度
     */
    public static void halfInsertSout(int[] arr) {         //注意方法能改变待排序数组(数组本质上是引用!!!!)
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
     * 3 3)插入排序之希尔排序(升序)
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tmp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                }
                arr[j] = tmp;
            }
        }
    }


    public static void main(String[] args) {
        int A[] = new int[]{23, 12, 66, 2, 100, 7};
        //1 交换排序-冒泡排序
//        int[] ints = ArraysSort.bubbleSort(new int[]{23, 12, 66, 2});
        //1 交换排序-快速排序
//        int[] quickSort = ArraysSort.quickSort(A, 0, A.length-1);
//        System.out.println(Arrays.toString(quickSort));       //使用于基本数据类型,其他可以使用:Arrays.toList()

        //2 选择排序-简单选择排序
//        int[] ints1 = ArraysSort.changeSort(new int[]{23, 12, 66, 8, 2});
        //2 选择排序-堆排序


        //插入排序01 - 简单插入排序
//        int[] ints = directInsertSort(new int[]{45,2, 12, 66, 23});
        //插入排序02 - 折半插入排序(时间复杂度是o(nlog2n))
        halfInsertSout(A);
        //插入排序 - 希尔排序
//        shellSort(A);
//        System.out.println(Arrays.toString(A));

    }
}
