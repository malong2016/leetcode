package com.kwl.data01.myTest;


import com.kwl.data01.dataStructure.ListNode;
import com.kwl.data01.dataStructure.TreeNode;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/3 0:08
 */
public class MyTest01 {

    public int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length];    //除了本身之外的乘积
        pre[0] = 1;
        for(int i = 1; i < nums.length;i++){
            pre[i] = pre[i-1] * nums[i];
        }
        int back = 1;
        for(int i = nums.length - 1;i >= 0; i--){
            pre[i] = back * pre[i];
            back = back * nums[i];
        }
        return pre;
    }

}
