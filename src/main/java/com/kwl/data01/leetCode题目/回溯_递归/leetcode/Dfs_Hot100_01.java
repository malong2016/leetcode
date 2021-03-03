package com.kwl.data01.leetCode题目.回溯_递归.leetcode;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class Dfs_Hot100_01 {

    /**
     * 题目1(leetcode 78题): 子集
     * 描述: 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * eg:nums = [1,2,3]  --> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * nums = [0]   --> [[],[0]]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();     //这是返回的结果集!!
        dfs(0,nums,res,new ArrayList<Integer>());
        return res;
    }
    public static void dfs(int i,int[] nums,List<List<Integer>> res,ArrayList<Integer> temp){
        res.add(new ArrayList<>(temp));      //要动态分配空间,temp总在变化,所以要动态分配
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j]);
            dfs(j+1,nums,res,temp);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 题目2(leetcode 46): 全排列
     * 描述: 给定一个 没有重复 数字的序列，返回其所有可能的全排列
     * eg: 输入: [1,2,3] --> {
     *       [1,2,3],
     *       [1,3,2],
     *       [2,1,3],
     *       [2,3,1],
     *      [3,1,2],
     *      [3,2,1]
     * }
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> outPut = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        for(int num:nums){
            outPut.add(num);
        }
        dfs(nums,0);
        return res;
    }
    void dfs(int[] nums,int first){
        if(first == nums.length) res.add(new ArrayList<>(outPut));   //如果遍历完成一轮,就添加到res中
        for (int i = first; i < nums.length; i++) {
            Collections.swap(outPut, first, i);
            dfs(nums, first+1);
            Collections.swap(outPut, first, i);    //回溯到原来的状态
        }
    }

    /**
     * 题目3(leetcode 39): 组合总和
     * 描述:给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明: 所有数字（包括 target）都是正整数。 解集(结果)不能包含重复的组合。
     * eg: 输入：candidates = [2,3,6,7], target = 7, --> {{7},{2,2,3}}
     */
    List<List<Integer>> res01 = new ArrayList<>();
    LinkedList<Integer> outPut01 = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs01(candidates,target,0);
        return res01;
    }
    void dfs01(int[] nums,int target,int begin){      //回溯
        if (target == 0) res01.add(new LinkedList<>(outPut01));
        for (int i = begin; i < nums.length; i++) {
            if (target - nums[i] < 0) break;   //结束本次循环的条件,也是回溯中的剪枝!!!,因为已经排序,所有自己拼接不了,后面的数也拼接不了!!!
//            if (target - nums[i] < 0) continue;   //结束本次循环的条件,也是回溯中的剪枝!!!
            outPut01.add(nums[i]);
            dfs01(nums,target-nums[i],i); //因为可以重复,所有继续从i开始寻找
            outPut01.removeLast();     //移除最新加入的值,注意这是一个双端队列,默认是移除最先加入的值(removeFirst())
        }
    }

    /**
     * 题目4(leetcode 22): 括号生成
     * 描述: 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * eg: n = 3 -->  ["((()))","(()())","(())()","()(())","()()()"]
     */
    private List<String> res03 = new ArrayList<>();


    public List<String> generateParenthesis(int n) {
        dfs("", n, 0, 0);
        return res03;
    }

    public void dfs(String ans, int n, int lc, int rc) {
        // 剪枝 右边扣号数量大于左边
        if(rc > lc || lc > n) return;
        // 满足结果
        if(lc == n && lc == rc) res03.add(ans);
        // 遍历+递归
        dfs(ans+'(', n, lc+1, rc);
        dfs(ans+')', n, lc, rc+1);
    }

    public static void main(String[] args) {
        Dfs_Hot100_01 skt = new Dfs_Hot100_01();
//        System.out.println("题目1(leetcode 78题): 子集:");
//        System.out.println(subsets(new int[]{1, 2, 3}));

//        System.out.println("题目2(leetcode 46): 全排列:");
//        System.out.println(new Dfs_Hot100_01().permute(new int[]{1,2,3}));

//        System.out.println("题目3(leetcode 39): 组合总和:");
//        System.out.println(skt.combinationSum(new int[]{2,7,6,3,5,1}, 9));

        System.out.println("题目4(leetcode 22): 括号生成");
        System.out.println(skt.generateParenthesis(3));
    }



}
