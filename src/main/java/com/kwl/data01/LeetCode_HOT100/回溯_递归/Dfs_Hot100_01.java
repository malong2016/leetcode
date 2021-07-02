package com.kwl.data01.LeetCode_HOT100.回溯_递归;

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
     * eg:nums = [1,2,3]  --> [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     * nums = [0]   --> [[],[0]]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();     //这是返回的结果集!!
        LinkedList<Integer> path = new LinkedList<>();
        dfs(0, nums, res, path);
        return res;
    }

    public static void dfs(int index, int[] nums, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new LinkedList<>(path));      //要动态分配空间,temp总在变化,所以要动态分配
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums, res, path);
            path.removeLast();
        }
    }

    /**
     * 题目2(leetcode 46): 全排列
     * 描述: 给定一个 没有重复 数字的序列，返回其所有可能的全排列
     * eg: 输入: [1,2,3] --> {
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * }
     */


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        dfs(nums, 0, res, path);
        return res;
    }

    void dfs(int[] nums, int index, List<List<Integer>> res, List<Integer> path) {
        if (index == nums.length) res.add(new ArrayList<>(path));   //如果遍历完成一轮,就添加到res中,在这里就已经做完了
        for (int i = index; i < nums.length; i++) {
            Collections.swap(path, index, i);
            dfs(nums, index + 1, res, path);
            Collections.swap(path, index, i);    //回溯到原来的状态
        }
    }

    public List<List<Integer>> permute01(int[] nums) {     //使用visit方法
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        System.out.println("我是全排列tmp = " + tmp);
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {     //总是从0开始搜索，等价于就是在交换元素
            if (visited[i] == 1) continue;     //搜索之后不能再搜索本身
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 题目3(leetcode 39): 组合总和
     * 描述:给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明: 所有数字（包括 target）都是正整数。 解集(结果)不能包含重复的组合。
     * eg: 输入：candidates = [2,3,6,7], target = 7, --> {{7},{2,2,3}}
     */


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        dfs01(candidates, target, 0,res,path);
        return res;
    }

    void dfs01(int[] nums, int target, int begin,List<List<Integer>> res, LinkedList<Integer> path) {      //回溯
        if (target == 0) res.add(new LinkedList<>(path));
        for (int i = begin; i < nums.length; i++) {
            if (target - nums[i] < 0) break;   //结束本次循环的条件,也是回溯中的剪枝!!!,因为已经排序,所有自己拼接不了,后面的数也拼接不了!!!
            path.add(nums[i]);
            dfs01(nums, target - nums[i], i,res,path); //因为可以重复,所有继续从i开始寻找
            path.removeLast();
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
        if (rc > lc || lc > n) return;
        // 满足结果
        if (lc == n && lc == rc) res03.add(ans);
        // 遍历+递归
        dfs(ans + '(', n, lc + 1, rc);
        dfs(ans + ')', n, lc, rc + 1);
    }

    public static void main(String[] args) {
        Dfs_Hot100_01 dfs_hot100_01 = new Dfs_Hot100_01();
        dfs_hot100_01.permute01(new int[]{1, 2, 3});
    }
}
