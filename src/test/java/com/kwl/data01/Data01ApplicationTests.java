package com.kwl.data01;

import com.kwl.data01.dataStructure.ListNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.plugin.javascript.navig.Link;

import java.util.*;

@SpringBootTest
class Data01ApplicationTests {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> outPut = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        for(int num:nums){
            outPut.add(num);
        }
        dfs(nums,0);
        return res;
    }
    void dfs(int[] nums,int first){       //假如是3
        if(first == nums.length) {
//            System.out.println(outPut);
            res.add(new ArrayList<>(outPut));   //如果遍历完成一轮,就添加到res中
        }
        for (int i = first; i < nums.length; i++) {
            Collections.swap(outPut, first, i);
            System.out.println("第一次交换"+outPut);
            dfs(nums, first+1);
            Collections.swap(outPut, first, i);    //回溯到原来的状态
            System.out.println("回溯:"+outPut);
        }
    }

    @Test
    void contextLoads() {
//        Data01ApplicationTests tests = new Data01ApplicationTests();
//        int[] arr = new int[]{1,2,3};
//        tests.permute(arr);
        ArrayList<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
    }

}
